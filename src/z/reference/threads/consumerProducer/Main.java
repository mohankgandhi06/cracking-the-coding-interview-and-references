package z.reference.threads.consumerProducer;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args)
	{
		Producer producer = new Producer();
		Consumer consumerOne = new Consumer();
		Consumer consumerTwo = new Consumer();

		producer.registerObserver(consumerOne);
		producer.registerObserver(consumerTwo);
		
		Thread producerThread = new Thread(producer, "producer");
		Thread consumerThreadOne = new Thread(consumerOne, "consumerOne");
		Thread consumerThreadTwo = new Thread(consumerTwo, "consumerTwo");
		
		producerThread.start();
		consumerThreadOne.start();
		consumerThreadTwo.start();
	}

	public static class Consumer implements Runnable, ProduceObserver {
		private volatile Produce produce = null;

		@Override
		public void onProduction(Produce produce) {
			this.produce = produce;
		}

		public void run()
		{
			System.out.println("Consumer starting");
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// Can ignore this
				}
				/*System.out.println("Produce Object: "+produce);*/
				if (this.produce != null) {
					int produceInstance = this.produce.getInstance();
					Produce.Color color = this.produce.getColor();
					System.out.println("Last produce instance: " + produceInstance);
					System.out.println("Last produce color: " + color.name());
					if (produceInstance == 10) {
						break;
					}
				}
			}
			System.out.println("Consumer terminating");
		}
	}

	public static class Produce {
		public static class ProduceBuilder {
			private int instance;
			private Color color;

			public void withInstance(int instance) {
				this.instance = instance;
			}

			public void withColor(Color color) {
				this.color = color;
			}

			public Produce build() {
				return new Produce(this.instance, this.color);
			}
		}

		enum Color {Red, Blue, Green, Yellow};
		private final int instance;
		private final Color color;

		private Produce(int instance, Color color) {
			this.instance = instance;
			this.color = color;
		}

		public int getInstance() {
			return this.instance;
		}

		public Color getColor() {
			return this.color;
		}
	}

	public static interface ProduceObserver {
		void onProduction(Produce produce);
	}

	public static class Producer implements Runnable {
		private volatile List<ProduceObserver> produceObservers = new ArrayList<>();

		public void registerObserver(ProduceObserver observer) {
			produceObservers.add(observer);
		}

		public void run()
		{
			System.out.println("Producer starting");

			for (int i=1; i<=10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Can ignore this
				}
				Produce.ProduceBuilder produce = new Produce.ProduceBuilder();
				produce.withInstance(i);
				produce.withColor(Produce.Color.values()[i % Produce.Color.values().length]);
				Produce latestProduce = produce.build();

				produceObservers.forEach(observer -> observer.onProduction(latestProduce));
			}

			System.out.println("Producer terminating");
		}
	}
}
