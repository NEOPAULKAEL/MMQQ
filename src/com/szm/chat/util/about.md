## 工具类
JDBC\
-getConnection()\
    获得连接\
-transactionController(Connection con)\
    设置事务不自动提交\
-commitTransaction(Connection con)\
    提交事务\
-rollbackTransaction(Connection con)\
    回滚事务\
-resultSetProcess(ResultSet rs)\
    结果集处理\
-release(Connection con, Statement stmt, ResultSet rs)\
    关闭释放资源