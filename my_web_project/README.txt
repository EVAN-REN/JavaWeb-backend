maven：Tools for managing and building Java projects
1.Easily and quickly manage project dependent resources (jar packages) to avoid version-conflict issues
2.Provide a standard, unified project structure(for eclipse, myeclipse10, intellij idea, ...)
3.Standard cross-platform (Linux, Windows, MacOS) automated way to build projects

可通过module直接建立web project，在maven管理中lifecycle中可以：
clean删除编译文件
compile编译项目,编译后的字节码文件放在target目录下
test测试项目，在test下可以测试
package将项目打成jar包，打包后文件放在target目录下
install将jar包安装到本地仓库目录

三层架构(便于开发，扩展，维护) controller -> service -> Dao
controller:控制层，接受前段发送的请求，对请求进行处理，并响应数据
（@RestController包含@Controller和@ResponseBody，@ResponseBody可以让直接使用return返回数据）
（@RequestMapping用来地址到controller的映射）
service:业务逻辑层，处理具体业务逻辑
dao:数据访问层(Data Access Object)，负责数据访问操作，包括数据的增、删、改、查

分层解耦:
内聚：软件中各个功能模块内部的功能联系
耦合：衡量软件中各个层/模块之间的依赖、关联的程度

为了实现高内聚，低耦合，将所需要创建的对象继承关系全部记录在一个文件中管理
控制反转：Inversion Of Control(IOC),对象的创建控制权有程序自身转移到外部
依赖注入：Dependency Injection(DI),容器为应用程序提供运行时，所依赖的资源
Bean对象：IOC容器中创建、管理的对象
通过以下方法将对象实现类交给ioc容器管理
@Component (三类之外)/@Controller (控制器类,@RestController已经包含)/@Service (业务类)/ @Repository (数据访问类)
对于实例化函数时调用 @Autowired 他会自动实现bean注入，但如果存在相同类型的bean时，
通过 @Primary/@Qualifier/@Resource 来解决
在创建的类上加上@Primary，会优先生效,或通过在@Autowired上加入@Qualifier()，里面填入bean的名字（默认是首字母小写的类名）
或者直接不用@Autowired，选择用@Resource(name = "")，填入bean的名字
（@Autowired与@Qualifier的区别：
@Autowired：是spring框架提供的，默认按照类型注入
@Qualifier：是JDK提供的注解，默认是按照名称注入）
@SpringBootApplication 会扫描当前目录下包和他们的子包来找到这些注解
对于其他目录下的包，可以通过 @ComponentScan 来指定扫描

默认情况下，spring项目启动时，会把bean都创建好放在ioc容器中，如果想要主动获取这些bean，可以通过如下方式：
根据name获取bean：Object getBean(String name)
根据类型获取bean：<T> T getBean(Class<T> requiredType)
根据name获取bean（带类型转换）：<T> T getBean(String name, Class<T> requiredType)

spring支持五种作用域，后三种在web环境中生效：
singleton:容器内同名称的bean只有一个实例（单例，默认）
prototype:每次使用该bean时会创建新的实例（非单例）
request:每个请求范围内会创建新的实例
session:每个会话范围内会创建新的实例
application:每个应用范围内会创建新的实例

第三方bean：
如果要管理的bean对象来自第三方（无法修改，不是自定义），是无法用@Component及衍生注解声明bean的，就需要用到@Bean注解
若要管理第三方bean对象，建议对这些bean进行集中分类配置，可以通过@Configuration注解声明一个配置类
通过@Bean注解的name或value属性可以声明bean的名称，如果不指定，默认bean的名称就是方法名
如果第三方bean需要依赖其他bean对象，直接在bean定义方法中设置形参即可，容器会根据类型自动装配
总结：项目中自定义的类，使用@Component及衍生注解；项目中引入第三方的，使用@Bean注解

MyBatis是一款优秀的持久层（dao）框架，用与简化JDBC的开发
1.准备工作（创建springboot工程、数据库表user、实体类User）
2.引入mybatis的相关依赖，配置mybatis（数据库连接信息，在resources下的properties中）
3.编写sql语句（注解/XML：@Mapper相当于@Repository并只用实现接口，调用时自动初始化实现类；@Select实现查询语句）
application.properties使用的 是数据库链接池，可以资源重用，提升系统响应速度，也能避免数据库连接遗漏
springboot自带的默认数据库链接池是Hikari

Lombok是一个使用的java类库，能通过注解的形式自动生成构造器/getter/setter/equals/hashcode/toString
代替了command+N手动生成
@Getter/@Setter:为所有属性提供get/set方法
@ToString:给类自动生成toString方法
@EqualsAndHashCode:根据类所拥有的飞静态字段自动重写equals方法和hashCode方法
@Data:提供了更综合的剩菜代码功能（包括上面三个）
@NoArgsConstructor:为实体类生成无参构造器
@AllArgsConstructor:为实体类生成除了static修饰字段外带有各参数的构造器方法

注解方法:
@Select/@Delete/@Update/@Insert等使用预编译SQL（性能更高，更安全，防止SQL注入）
#{}作为占位符，将内容作为整体参数传入
${}是运用拼接的方式传入，会出现SQL注入问题

数据封装
实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装
如果实体类属性名和树就表查询返回的字段名不一致，不能自动封装
方案一：给字段起别名，上别名与实体类属性一致
方案二：通过@Results, @Result注解手动映射封装
方案三：开启mybatis的驼峰命名自动映射开关 --- a_column -> aColumn

XML映射文件方法:
xml映射文件的名称与mapper接口名称一致，并且将xml映射文件和mapper接口放置在相同包下（同包同名在resources下）
xml映射文件的namespace属性为mapper接口全限定名一致
xml映射文件中sql语句的id与mapper接口中的方法名一致，并保持返回类型一致

动态sql：随着用户的输入或外部条件的变化而变化的sql语句
<if>(是否拼接字段)/<where>(1.动态是否生成where关键字 2.自动生成或删除and或or)/<set>(去掉多余的,)
<foreach>:批量操作(collection:遍历的集合,item:遍历出来的元素,separator:分隔符,oprn:遍历开始前拼接的sql片段,close:遍历结束后拼接的sql片段)
<sql>封装sql语句/<include>调用封装的sql语句

Restful开发规范（url定位资源，http动词描述操作）
get：查询操作
post：新增操作
put：修改操作
delete：删除操作

开发流程：
查看页面原型明确需求--阅读接口文档--思路分析--接口开发--接口测试--前后端联调

分页插件PageHelper
通过pagehelper里设置分页参数，在查询时自动帮住查询count和数据分页
把相关结果封装到page类中，从而可以直接从page类获得所有相关信息
加入依赖:pagehelper-spring-boot-starter

文件上传：
前段: <form>中要有一个<input type="file">;
<form method="post" enctype="multipart/form-data"> 提交方式为post，编码格式还要适合大型数据传输
后段接受：MultipartFile

文件大小：(在application.properties中配置 )
配置单个文件最大上传大小（默认1MB）
spring.servlet.multipart.max-file-size=10MB
配置单个请求最大上传大小（一次请求可以上传多个文件，默认10MB）
spring.servlet.multipart.max-request-size=100MB

文件本地存储：(大量存储，不易扩容；本地磁盘可能损坏；前段无法直接访问)
(MultipartFile)file.transferTo(new File(path))
文件名构造要唯一，所以要使用uuid（通用唯一识别码）
UUID.randomUUID()

云存储可以选择：阿里云对象存储(OSS)

@Value注解通常用于外部配置的属性注入，具体用法为：@Value("${配置文件中的key}")
例如：
类中：
public class AliOSSUtils{
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
}
application.properties中：
aliyun.oss.endpoint=https://oss-cn-hangzhou.aliyuncs.com

使用注解@ConfigurationProperties 可以更加方便(可以加入spring-boot-configuration-processor依赖可以取消警告并提供提示)
1.要保证properties/yml中的名字的后缀和类中的成员变量名字相同
2.在类上加入@Data添加get/set方法，并加上@Componenth使类加入到ioc容器中
3.在类上加入@ConfigurationProperties(prefix = "配置文件中名字的前缀")

@Value和@ConfigurationProperties都能注入外部配置的属性
@Value只能一个一个进行外部属性的注入， @ConfigurationProperties可以批量注入到bean对象属性中

yaml/yml(application.yaml/application.yml)配置文件(不同于application.properties)
这三种配置方式优先级从高到低：program arguments(命令行参数,--xxx=xxx),VM options(java系统属性,-Dxxx=xxx),properties,yml(主流),yaml
properties配置方式(无层级)：key=value
server.port=8080
server.address=127.0.0.1
yaml/yml配置方式(有层级)：key: value
server
    port: 8080
    address: 127.0.0.1
xml配置方式：
<server>
    <port>8080</port>
    <address>127.0.0.1</address>
</server>

实现登陆校验：登陆标记/统一拦截
登陆标记（会话技术）：用户登录成功之后，每一次请求中，都可以获取到该标记
统一拦截：过滤器(Filter)、拦截器(Interceptor)

会话技术：
会话：用户打开浏览器，访问web服务器的资源，会话建立，直到有一方断开连接，会话结束。在一次会话中可以包含多次请求和响应
会话跟踪：一种维护浏览器状态的方法，服务器需要识别多次请求是否来自同一浏览器，以便在同一次会话的多次请求间共享数据
会话跟踪方案：
1.客户端会话跟踪技术：Cookie(服务器通过response发送cookie值给浏览器，浏览器二次访问时带上cookie值在服务器request核实)
优点：http协议中支持的技术
缺点：移动端app无法使用cookie/不安全，用户可以自己禁用cookie/cookie不能跨域(当前段服务器和后段服务器不是同一个地址时)
2.服务端会话跟踪技术：Session(基于cookie，服务器会创建session，通过cookie响应浏览器对应的session ID，浏览次每次请求也携带session ID找到对应session)
优点：存储在服务端，安全
缺点：服务器集群环境(分布式)下无法直接使用session(处理负载均衡的服务器会将客户端request发送到随机前端服务器，可能导致两次访问服务器不同，session ID找不到)/基于cookie，拥有cookie所有缺点
3.令牌技术(JWT令牌)(JSON Web Token)
优点：支持PC端、移动端/解决集群环境下的认证问题/减轻服务器端存储压力
组成：
第一部分：header，记录令牌类型，签名算法
第二部分：payload，携带一些自定义信息、默认信息等
第三部分：signature，防止token被篡改、确保安全性。将header、payload，并加入指定密钥，通过指定签名算法计算而来
登陆成功之后，生成令牌，后续每个请求需要携带jwt令牌，系统在每次请求处理之前，先校验令牌，通过后，再处理
需要依赖 jjwt

Filter过滤器，是JavaWeb三大组件(Servlet,Filter,Listener)之一
过滤器可以把对资源的请求拦截下来，从而实现一些特殊的功能
过滤器一般完成一些通用的操作，比如：登陆校验、统一编码处理、敏感字符处理等
步骤
1:定义Filter：定义一个类，实现Filter接口，并重写其所有方法
2:配置Filter：Filter类上加@WebFilter注解，通过拦截资源的路径。引导类上加@ServletComponentScan开启组建支持
拦截路径：/login 、 /depts/* 、 /*
过滤器链：一个web应用中，可以配置多个过滤器，这就形成了一个过滤器链
顺序：注解配置的Filter，优先级是按照过滤器类名（字符串）的自然排序

拦截器interceptor
是一种动态拦截方法调用的机制，类似于过滤器。spring框架中提供的，用来动态拦截控制器方法的执行
拦截请求，在指定的方法调用前后，根据业务需要执行预先设定的代码
步骤：
1 定义拦截器，实现HandlerInterceptor接口，并重写其所有方法，加上@Component交给ioc容器管理
2 创建一个配置类（加上@Configuration）继承WebMvcConfigurer，注册拦截器（创建一个拦截器类，加上@Autowired），通过addInterceptor添加
在后面通过加上addPathPatterns来表示需要拦截哪些资源，excludePathPatterns不需要添加哪些资源
拦截路径：/* 一级路径、/** 任意级路径, /depts/*, /depts/**
执行流程：浏览器 -> (JavaWeb)Filter -> (spring)DispatcherServlet -> Interceptor -> Controller

Filter与Interceptor区别
接口规范不同：过滤取需要实现filter接口，拦截器需要实现handlerinterceptor接口
拦截范围不同：过滤取filter会拦截所有资源，而interceptor只会拦截spring环境中的资源

异常处理
方案一：在每一个controller的方法中进行try/catch处理
方法二：全局异常处理器（推荐）
步骤：
创建异常处理类，加上@RestControllerAdvice(@ControllerAdvice+@ResponseBody)，方法上加上@ExceptionHandler(异常类型)

事务管理
事务是一组操作的集合，他是一个不可分割的工作单位，这些操作要么同时成功，要么同时失败
开启事务（一组操作开始前，开启事务）：start transaction / begin ;
提交事务（这组操作全部成功后，提交事务）：commit ;
回滚事务（中间任何一个操作出现异常，回滚事务）：rollback ;

Spring事务管理
注解：@Transactional
位置：业务（service）层的方法上、类上、接口上
作用：将当前方法交给spring进行事务管理，方法执行前，开启事物；成功执行完毕，提交事务；出现异常，回滚事务
事务属性：
回滚（rollbackFor）：默认情况下，只有出现RuntimeException才回滚异常。rollbackFor属性用于控制出现何种异常类型，回滚事务
传播行为（propagation）：指的就是当一个事务方法被另一个事务方法调用时，这个事务方法应该如何进行事务控制
REQUIRED  默认，需要事务，有则加入，无则创建新事务
REQUIRES_NEW  需要新事务，无论有无，总是创建新事务
SUPPORTS  支持事务，有则加入，无则在无事务状态中运行
NOT_SUPPORTED  不支持事务，在无事务状态下运行，如果当前存在已有事务，则挂起当前事务
MANDATORY  必须有事务，否则泡异常
NEVER  必须无事务，否则抛异常

AOP: Aspect Oriented Programming(面向切面编程、面向方面编程)，其实就是面向特定方法编程
一句话总结aop：将与核心业务无关的代码独立的抽取出来，形成一个独立的组件，然后以横向交叉的方式应用到业务流程当中的过程
动态代理是面向切面编程最主流的实现。而SpringAOP是Spring框架的高级技术，旨在管理bean对象的过程中，主要通过底层动态代理机制，对特定的方法进行编程
使用场景：记录操作日志/权限控制/事务管理
优点：代码无侵入/减少重复代码/提高开发效率/维护方便
步骤：
导入依赖：在pom.xml中导入AOP的依赖
编写AOP程序：针对于特定方法根据业务需要进行编程
在AOP类上加注解@Aspect
在AOP类中的方法上加上注解@Around()来表明哪些方法使用AOP

AOP核心概念：
链接点：JoinPoint，可以被AOP控制的方法（暗含方法执行时的相关信息）
通知：Advice，指那些重复的逻辑（比如查询方法执行时间），也就是共性功能（最终体现为一个方法）
切入点：PointCut，匹配连接点的条件，通知仅会在切入点方法执行时被应用
切面：Aspect，描述通知和切入点的对应关系（通知+切入点），切入点+通知=切面
执行流程：
程序进入controller层，aop会将controller层中的初始化的service层对象变为代理对象（代理对象就是对目标对象增强后的对象）
在代理对象中存在目标对象以及通知方法，所以程序会进入aop类中的方法执行
当执行到aop类方法中的原始方法时，会跳转到目标对象中的方法
当原始方法运行完成，程序会回到aop类中的方法执行
完成后回到controller层返回结果

通知类型：
@Around:环绕通知，此注解标注的通知方法在目标方法前、后都被执行(注意：需要自己调用ProceedingJoinPoint.proceed()来让原始方法执行，其他通知不考虑；此通知方法返回值必须指定为object，来接收原始方法的返回值)
@Before:前置通知，此注解标注的通知方法在目标方法前被执行
@After:后置通知，此注解标注的通知方法在目标方法后被执行，无论是否有异常都会执行
@AfterReturning:返回后通知，此注解标注的通知方法在目标方法后被执行，有异常不会执行
@AfterThrowing:异常后通知，此注解标注的通知方法发生异常后执行
@PointCut:可以将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可（注意如果其他类要调用方法，pointcut所注释的方法在其本身类中应该是public）

通知顺序：
默认是类名越靠前before越先执行，after越后执行
可以加入@Order(数字)里面数字越小before越先执行，after越后执行

切入点表达式：
1. execution(访问修饰符 返回值 包名.类名.方法名(方法参数) throws 异常)
访问修饰符、包名.类名、throws异常都可以省略
*:单个独立的任意符号，可以是通配任意返回值、包名、类名、方法名、任意类型一个参数，或是他们其中的一部分
..:多个连续的任意符号，可以是任意层级的包、任意类型个数的参数
2. @annotation()用于匹配标识有特定注解的方法
先自己创建一个自定义注解类，在方法上加上自定义注解，用@annotation()在aop类中声明在自定义注解上生效

连接点：在Spring中用JoinPoint抽象了连接点，用它可以获得方法执行时的相关信息，如目标类名、方法名、方法参数等
@Around通知中，获取连接点信息只能用ProceedingJoinPoint
对于其他四种通知，获取连接点信息只能用JointPoint，他是ProceedingJoinPoint的父类型

springboot原理：（起步依赖，自动配置）
起步依赖：利用springboot的依赖传递
自动配置：springboot在spring容器启动后，一些配置类、bean对象就自动存入到ioc容器中，不需要我们手动去声明，从而简化了开发，省去了繁琐的配置操作
方案一：通过@ComponentScan组建扫描来获取第三方的包
方案二：通过@Import导入，使用@Import导入的类会被spring加载到ioc容器中，可以导入普通类/配置类/ImportSelector接口实现类，或者使用第三方包的@EnableXxxx注解，封装了@Import注解

对于spring启动注解 @SpringBootApplication：
该注解标识在SpringBoot工程引导类上，是SpringBoot中最最最重要的注解，该注解由三个部分组成：
@SpringBootConfiguration:该注解与@Configuration注解作用相同，用来声明当前也是一个配置类
@ComponentScan:组建扫描，默认扫描当前引导类所在包及其子包
@EnableAutoConfiguration:SpringBoot实现自动化配置的核心注解，里面有一个@Import，里面是AutoConfigurationImportSelector类型
此类中有一个方法是selectImports会获取maven管理的spring-boot-autoconfigure中META-INF中spring里文件，里面是所有自动配置的类名

@Conditional:（当上面spring启动自动加载类到bean时，会根据以下注解来选择是否加入）
作用：按照一定的条件进行判断，在满足给定条件后才会注册对应的bean对象到Spring IOC容器中
位置：方法、类
@Conditional本身是一个父注解，派生出大量的子注解：
@ConditionalOnClass:判断环境中是否有对应字节码文件，才注册bean到ioc容器
@ConditionalOnMissingBean:判断环境中没有对应的bean（类型或名称），才注册bean到ioc容器
@ConditionalOnProperty:判断配置文件中有对应属性和值，才注册bean到ioc容器

自定义starter（例如自定义aliyun-oss-spring-boot-starter，完成阿里云oss操作工具类AliyunOSSUtils的自动配置）
1.创建aliyun-oss-spring-boot-starter模块
2.创建aliyun-oss-spring-boot-autoconfigure模块，在starter中引入该模块
3.在aliyun-oss-spring-boot-autoconfigure模块中的定义自动装配功能，并定义自动配置文件META-INF/spring/xxx.imports

总结：
解决方案：阿里云oss，JWT
javaweb：过滤器，cookie、session
springboot
spring framework：IOC，DI，AOP，事务管理
springmvc：拦截器，全剧异常处理，接受请求，响应数据
mybaits