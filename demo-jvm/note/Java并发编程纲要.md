Java并发编程纲要
    Java并发编程基本模型: 线程与锁
		特点: 线程与锁的模型是底层系统机制的反映, 机制简单原始, 使用复杂易错, 难以测试
		线程之间使用共享内存进行通信, 但多个线程同时访问共享内存(临界区), 会导致一系列问题(哪些?)
			竟态条件(未加锁): 代码的行为结果取决于线程操作时序. (rmw: 读改写模式)
			乱序执行: 在保证单线程的顺序语义下, 由于编译器, 虚拟机或硬件优化可能导致乱序执行.
			内存可见性: 一个线程的修改在另一线程下不可见.
				JMM(Java Memory Model, Java内存模型, JSR133)定义了一个线程的修改何时对另一个线程可见, 基本原则为: 读和写线程不同步就不保证可见性.
			线程活跃性问题:
				死锁: 同一线程获得多个锁, 就可能, 可用哲学家问题来阐述, 可通过总按照全局固定的顺序获得锁来解决, 注意类似于监听者模式中的外部方法导致的死锁.
				活锁:
				饥饿:
		为了避免问题, 可以使用锁(原生锁, 互斥锁, monitor)来达到线程互斥的目的, 即某一时刻有且仅有一个线程可以访问共享内存, 其他线程将被阻塞至所释放.
		内置锁的问题:
			一个线程应为等待内置锁而阻塞后就无法中断, 可以推出进入死锁的程序, 关闭JVM是唯一办法.
			无法超时
			必须使用synchronized关键字, 获取某对象的锁
		
		
	并行与并发基本概念
	线程与进程基本概念
		线程是Java并发的基本单元
		Thread类是Java线程的抽象
			Thread.yield()方法用于通知建议调度器, 当前线程要出让处理器的占用.
			
			thread.join(); 当前线程等待thread线程结束.
			
	并发的实现:
		语言层面:
			synchronized 和 volatile 关键字
		Java虚拟机层面:
			同步代码块的monitorenter和monitorexit, 同步方法的ACC_SYNCHRONIZED标志
		
		类库层面:
			juc:
			juc.locks:
				AbstractOwnableSynchronizer
					AbstractQueuedSynchronizer(AQS)
					AbstractQueuedLongSynchronizer
				Condition
				Lock
				LockSupport
				ReadWriteLock
				ReentrantLock
				ReentrantReadWriteLock
			juc.atomic:
			线程池
			并发容器
				
		第三方类库及框架:
			Akka
			disruptor
			
		模式:
			单例模式与多线程, DCL的问题
	
	Java并发实践
		尽量使用并发上层服务, 即JUC中提供的接口与类, 不应在产品中直接使用Thread等底层服务.
		对于只涉及一个变量的互斥场景可以使用juc.atomic包是更好的选择.
		好的并发程序总是在保证线程安全的基础上, 尽量提升效率.
      