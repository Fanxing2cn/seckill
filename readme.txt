《JAVA高并发-秒杀商品》 项目中使用了流行的框架组合SpringMVC+Spring+MyBatis
 
1. 创建maven  web项目   项目采用 springmvc + spring + hibernate框架

2. 
         设置 servlet3.0  web.xml   2.3默认关闭el
          到Tomcat8.0 apache-tomcat-8.0.52\webapps\manager 下找web.xml copy
          <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">
  
3. 使用 junit 4.11

4. 所有依赖
 <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
     <!-- JSP标准标签库 -->
      <dependency>
          <groupId>javax.servlet</groupId>
          <artifactId>jstl</artifactId>
          <version>1.2</version>
      </dependency>
    <!-- 日志相关包 -->
     <dependency>
         <groupId>log4j</groupId>
         <artifactId>log4j</artifactId>
         <version>1.2.17</version>
     </dependency>
     <!-- 数据库的依赖  -->
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.1.30</version>
	</dependency>
     <!-- c3p0 连接池配置 -->
	<dependency>
	    <groupId>com.mchange</groupId>
	    <artifactId>c3p0</artifactId>
	    <version>0.9.5</version>
	</dependency>
     <!-- mybatis 依赖  -->
     <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>   
    </dependency>
    <!-- mybatis 跟spring整合 依赖  -->
     <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.3</version>   
    </dependency>
    <!-- google JSON gson -->
	<dependency>
	    <groupId>com.google.code.gson</groupId>
	    <artifactId>gson</artifactId>
	    <version>2.5</version>
	</dependency>
	<!-- spring 核心 --> 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
      
	<!-- spring jdbc -->
	 <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-jdbc</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-tx</artifactId>
          <version>${spring.version}</version>
      </dependency>
	<!-- spring web -->
	  <dependency>
	      <groupId>org.springframework</groupId>
	      <artifactId>spring-web</artifactId>
	      <version>${spring.version}</version>
	  </dependency>
	  <dependency>
	      <groupId>org.springframework</groupId>
	      <artifactId>spring-webmvc</artifactId>
	      <version>${spring.version}</version>
	  </dependency>
	<!-- spring test依赖 -->
	<dependency>
	      <groupId>org.springframework</groupId>
	      <artifactId>spring-test</artifactId>
	      <version>${spring.version}</version>
	  </dependency>  
  </dependencies>
 
 
 5. 完成entity 的两个类
 
 6. 完成DAO 接口
 
 7. 基于myBatis 实现DAO  建立mybatis-config.xml
 官网查询
 http://www.mybatis.org/mybatis-3/zh/getting-started.html
 
8. spring+ mybatis 整合，让你不用写实现类 ，爽
        写spring-dao.xml
        
9.写 junit 测试 dao 接口
    测试Seckill queryById(long seckillId) 方法是正常
 
    测试 下面方法是报错，原因是java编译器   queryAll(arg0,arg1) 会搞不清哪个参数是哪个了 
  List<Seckill> queryAll(int offset,int limit);
  
      加上注解就没有坑了
  List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
  
  DAO 接口里方法，返回值是对象的，一定要设置resultType ,例如
  <select id="queryById" resultType="Seckill" parameterType="long">
		select * from seckill where seckill_id = #{seckillId}
	</select>
  
  
 
    