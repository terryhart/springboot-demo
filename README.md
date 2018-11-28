# springboot-demo
springboot项目，新能源车后台管理系统<br/>
架构及技术点：<br/>
  1.前后端完全分离，独立部署，用户通过请求头token进行识别访问，后端进行跨域处理；<br/>
  2.拦截器配合自定义注解，验证用户是否登录以及权限信息;<br/>
  3.aop配合自定义注解记录请求返回信息日志，响应时间、错误信息、当前访问用户等；<br/>
  4.全局异常处理，保证异常不会抛到前端，并且返回响应的错误码和提示；<br/>
  5.对spring异步线程池进行增强，子线程也能正确获取到ThreadLocal信息，如当前用户信息；<br/>
  6.使用spring的接口参数校验，保证后端接口的健壮性；<br/>
  7.使用swagger-ui接口文档，接口文档更易维护，且方便调试；<br/>
  8.@Cacheable的使用，对于频繁查询的用户信息，以及不常修改的配置信息进行缓存;<br/>
  9.涉及框架技术：springboot、mybtais、redis、mysql、nginx<br/>
