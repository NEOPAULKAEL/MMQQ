业务逻辑层
业务逻辑设计

用户业务逻辑：
1.普通用户
UserService接口，UserService实现类；
IOnLineUserService接口，OnLineService实现类；
    注册（增加）、登录（查询）、查询个人信息、修改个人信息、记录在线用户、查看在线用户

2.管理员 
IAdminService接口,AdminService实现类
    包括普通用户功能
    删除用户(根据用户名或用户id)，授权普通用户(根据用户名或ID)、查看所有用户
		
3.短信消息业务逻辑：
IMessageService接口,MessageService
    发送私聊消息、接收私聊消息、发送群聊消息、接收群聊消息
    记录聊天消息、文件发送、文件接收、群发文件、接收群发文件

4.历史聊天记录业务逻辑：
IHistoryService,HistoryService  
    查看历史聊天记录
