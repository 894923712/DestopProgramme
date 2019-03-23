package com.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.Const.SQLConst;
import com.bean.Reader_info;

public class Reader_Info_Dao {
	
	public boolean insertReaderInfo(String readerid,String name,String sex,String age,String job,String zj,String identityCard,String maxnum,String date,String tel,String keepmoney,String bztime) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.INSERT_READER_INFO);
		pstas.setString(1, readerid);
		pstas.setString(2, name);
		pstas.setString(3, sex);
		pstas.setInt(4, Integer.parseInt(age));
		pstas.setString(5, job);
		pstas.setString(6, zj);
		pstas.setString(7, identityCard);
		pstas.setInt(8, Integer.parseInt(maxnum));
		pstas.setObject(9, new java.sql.Timestamp(new Date(date).getTime()));
		pstas.setString(10, tel);
		pstas.setString(11, keepmoney);	
		pstas.setObject(12, new java.sql.Timestamp(new Date(bztime).getTime()));
		return pstas.executeUpdate()>0;
		
	}
	public List<Reader_info> selectReaderInfo() throws Exception{
		List<Reader_info> readerinfoList=new ArrayList<Reader_info>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_READER_INFO_ALL);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Reader_info reader_info=new Reader_info();
			reader_info.setISBN(rs.getString("readerid"));			
			reader_info.setName(rs.getString("name"));
			reader_info.setAge(rs.getInt("age"));
			reader_info.setSex(rs.getString("sex"));
			reader_info.setIdentityCard(rs.getString("identitycard"));
			reader_info.setMaxnum(rs.getInt("maxnum"));
			reader_info.setDate(rs.getTimestamp("date"));
			reader_info.setTel(rs.getString("tel"));
			reader_info.setKeepmoney(rs.getDouble("keepmoney"));
			reader_info.setZj(rs.getString("zj"));
			reader_info.setJob(rs.getString("job"));
			reader_info.setBzTime(rs.getTimestamp("bztime"));
			readerinfoList.add(reader_info);
		}
		return readerinfoList;	
	}
}
