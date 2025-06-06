# this关键字

在前面的文章中我们提到了使用`private`保障数据的安全，但是在使用set和get方法的时候，传入参数我们的命名并不规范，因为命名要遵守见名知意，显然在setName中我们传入的参数用n来接收，这个n就不符合见名知意，我们不能看到n就能知道他的意思，既然这样最合适的变量名还是name，为了解决这一问题，就有了this关键字

## 成员变量和局部变量

定义在方法里面的叫做局部变量，定义在方法外面，类里面的叫做成员变量

![image-20250114014648703](https://pic.hibugs.net/NGBTEAM/image-20250114014648703.png)

上述这段代码中，如果局部变量与成员变量的变量名一致，那么在输出的时候不知道到底输出哪个。那么关于这涉及到一个知识：**就近原则：谁离我近，我就用谁**

![image-20250114014934431](https://pic.hibugs.net/NGBTEAM/image-20250114014934431.png)

所以这里根据就近原则，局部变量离这个输出语句最近，所以就会打印这个局部变量。

但是如果在这里我就想打印成员变量的age，而不想打印局部变量的age怎么办呢？

## this关键字的使用

这时候我们就需要使用this关键字，让其打印成员变量的age

![image-20250114015144476](https://pic.hibugs.net/NGBTEAM/image-20250114015144476.png)

学习了this关键字后，改写set和get的方法代码就会得到如下代码

![image-20250114020035503](https://pic.hibugs.net/NGBTEAM/image-20250114020035503.png)

这样就符合了见名知意的原则了

## this的作用

可以区别成员变量和局部变量