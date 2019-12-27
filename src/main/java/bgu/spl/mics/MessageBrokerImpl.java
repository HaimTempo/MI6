package bgu.spl.mics;

import bgu.spl.mics.application.messages.TerminationEvent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The {@link MessageBrokerImpl class is the implementation of the MessageBroker interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */

public class MessageBrokerImpl implements MessageBroker {
	private ConcurrentHashMap<Class<? extends Message>,ConcurrentLinkedQueue<Subscriber>> subscribersToMessage;
	private ConcurrentHashMap<Subscriber,ConcurrentLinkedQueue<Message>> messagesToSubscriber;
	private ConcurrentHashMap<Event<?>, Future> eventFuture;
	private static class SingleHolder{
		static private MessageBrokerImpl instance=new MessageBrokerImpl();
	}

	private MessageBrokerImpl(){
		subscribersToMessage=new ConcurrentHashMap<>();
		messagesToSubscriber=new ConcurrentHashMap<>();
		eventFuture=new ConcurrentHashMap<>();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static MessageBroker getInstance() {
		return SingleHolder.instance;
	}

	private void subscribe(Class<? extends Message> type, Subscriber m) {
		synchronized (this) {//so we dont add twice
			subscribersToMessage.putIfAbsent(type, new ConcurrentLinkedQueue<>());
		}
		subscribersToMessage.get(type).add(m);
	}


	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, Subscriber m) {
		subscribe(type,m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, Subscriber m) {
		subscribe(type,m);
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		eventFuture.get(e).resolve(result);
	}

	@Override
	public void sendBroadcast(Broadcast b) {
		System.out.println("send Event "+b.getClass());
		ConcurrentLinkedQueue<Subscriber> allBsubs = subscribersToMessage.get(b.getClass());
		if (allBsubs == null) {
			return;
		}
		for (Subscriber subscriber : allBsubs) {
			messagesToSubscriber.get(subscriber).add(b);
			synchronized (subscriber) {
				subscriber.notifyAll();
			}
		}
	}

	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		System.out.println("send Event "+e.getClass());
		Subscriber sub;
		ConcurrentLinkedQueue<Subscriber> subs = subscribersToMessage.get(e.getClass());
		if(subs==null) 
			return null;

		synchronized (e.getClass()) {
			if(subs.isEmpty())
				return null;
			sub = subs.poll();
			subs.add(sub);
		}

		Future<T> future=new Future<T>();
		eventFuture.putIfAbsent(e,future);
		synchronized (sub) {
			ConcurrentLinkedQueue<Message> q=messagesToSubscriber.get(sub);
			if (q == null)
				return null;
			q.add(e);
			sub.notifyAll();
		}
		return future;
	}

	@Override
	public void register(Subscriber m) {
		ConcurrentLinkedQueue<Message> bq = new ConcurrentLinkedQueue<>();
		messagesToSubscriber.putIfAbsent(m,bq);
	}

	@Override
	public void unregister(Subscriber m) {
		subscribersToMessage.forEach((msg_class,q)->{
			synchronized (msg_class) {
				q.remove(m);
			}
		});
		ConcurrentLinkedQueue<Message> messages;
		synchronized (m){
			if (!messagesToSubscriber.containsKey(m)) {
				System.out.println(m.getName() + "wwwwwwwwwwwwwwwwwwwwwwwwwUnreg");
			}
			else
			{
				System.out.println(m.getName() + " fine Unreg");
				messages = messagesToSubscriber.get(m);
				while (!messages.isEmpty()) {
					Message message = messages.poll();
					Future<?> future = eventFuture.get(message);
					if (future != null)
						future.resolve(null);
				}
				messagesToSubscriber.remove(m);
			}
		}
	}

	@Override
	public Message awaitMessage(Subscriber m){
		//TODO i think we need to test that it is a legal sub

		ConcurrentLinkedQueue<Message> messages=messagesToSubscriber.get(m);
		synchronized (m) {
			while (messages.isEmpty()) {

				try {
					m.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return messages.poll();
	}
}
