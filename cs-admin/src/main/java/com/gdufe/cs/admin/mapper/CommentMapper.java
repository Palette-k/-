package com.gdufe.cs.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.admin.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment>{


//    @Select("SELECT * from comment ")
 //   List<Comment> findAll();
//
  //  @Insert("INSERT into comment(id,parent_id,type,commentator,gmt_create,gmt_modified,like_count,comment_count,content)" +
  //          " VALUES (#{id},#{parent_id},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{like_count},#{comment_count},#{content})")
  //  int insert(Comment comment);

  //  int update(Comment comment);

 //   @Delete("delete from comment where id=#{id}")
  //  Integer deleteById(@Param("id") Integer id);

 //   @Select("select * from comment limit #{pageNum}, #{pageSize}")
  //  List<Comment> selectPage(Integer pageNum, Integer pageSize);

 //   @Select("select count(*) from comment")
  //  Integer selectTotal();

}



