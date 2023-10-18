maven：Tools for managing and building Java projects
1.Easily and quickly manage project dependent resources (jar packages) to avoid version-conflict issues
2.Provide a standard, unified project structure(for eclipse, myeclipse10, intellij idea, ...)
3.Standard cross-platform (Linux, Windows, MacOS) automated way to build projects

可通过module直接建立web project，在maven管理中lifecycle中可以：
clean删除编译文件
compile编译项目
test测试项目
package将项目打成jar包
install将jar包安装到本地目录

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