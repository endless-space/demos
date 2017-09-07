网络编程的基本模型是C/S模型，即两个进程间的通信

Java类库中提供了3种网络IO编程接口:
	BIO: 阻塞
	NIO: 非阻塞
	AIO: 异步

BIO:
	最早提供的同步IO接口, 基于流, 装饰者模式
	InputStream/OutputStream
	
NIO:
	1.4中提供的non-blocking IO 
	Buffer / Selector / Channel
	
AIO:
	AsynchrousServerSocketChannel / CompletionHandler

网络编程接口在JVM中的实现:	
	