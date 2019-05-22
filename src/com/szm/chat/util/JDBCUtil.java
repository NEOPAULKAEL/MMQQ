/**
 * @program: IDEAPROJECTS
 * * @description: JDBC工具类-模块化
 * * @author:cro
 * * @create: 2019-05-05 13:23
 **/

package com.szm.chat.util;

import java.sql.*;

public class JDBCUtil {
    //定义驱动
    public static final String ORACLEDRIVER="oracle.jdbc.driver.OracleDriver";
    //登录-用户名和密码
    public static final String USERNAME="scott";
    public static final String PASSWORD="scott";
    //URL连接协议
    public static final String URL="jdbc:oracle:thin:@127.0.0.1:1521:xe";
    //加载驱动
    static {
        try {
            Class.forName(ORACLEDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //建立连接
    public static Connection getConnection(){
        Connection con=null;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //事务控制-设置不自动提交
    public static void transactionController(Connection con){
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交
    public static void commitTransaction(Connection con){
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //回滚
    public static void rollbackTransaction(Connection con){
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    *结果集处理
    * 由于每个表格返回的结果集结构有所不同
    * 可以使用ResultSetMetaData获取结果集的结构
    * 再配合方法输出
     */
    public static String resultSetProcess(ResultSet rs){
        StringBuffer sb=null;//存放结果集
        if(rs!=null){
            try {
                ResultSetMetaData rsmd=rs.getMetaData();//根据结果集获取结果集的元数据
                int colsnum=rsmd.getColumnCount();//获取结果集行数
                sb=new StringBuffer();
                for (int i=1;i<=colsnum;i++){
                    String colName=rsmd.getColumnName(i);//获取字段名
                    int size=rsmd.getColumnDisplaySize(i);//获取字段最大长度
                    if (colName==null) colName="null";//字段为空的话，该字段名字就叫null
                    /*
                    * 显示格式设置
                    * 所有列行对齐显示
                     */
                    //输出字段名
                    int b=(size-colName.length())/2;//判断该字段名两端需要添加的空格数
                    if (b>0){//字段名不等于字段最长显示范围
                        for (int j=0;j<b;j++)sb.append(" ");
                        sb.append(colName);
                        for (int j=0;j<b;j++)sb.append(" ");//”补的空格“+字段名+”补的空格“
                        if ((size-colName.length())%2!=0)sb.append(" ");//奇数字段名，后面多补一个空格
                    }else {
                        sb.append(colName.substring(0,size));//字段名长了，截取从0到size的这一段
                    }
                    sb.append("\n");
                }
                //输出值
                while (rs.next()){
                    for (int i=1;i<=colsnum;i++){
                        int size=rsmd.getColumnDisplaySize(i);
                        String colValue=rs.getString(i);
                        if (colValue==null)colValue="null";
                        int b=(size-colValue.length())/2;
                        if (b>0){
                            for (int j=0;j<b;j++)sb.append(" ");
                            sb.append(colValue);
                            for(int j=0;j<b;j++)sb.append(" ");
                            if((size-colValue.length())%2!=0)sb.append(" ");
                        }else{
                            sb.append(colValue.substring(0,size));
                        }
                    }
                    sb.append("\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //关闭-释放资源,关闭连接、statement对象、结果集
    public static void release(Connection con, Statement stmt, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
