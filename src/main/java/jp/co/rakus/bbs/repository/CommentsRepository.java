package jp.co.rakus.bbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.bbs.domain.Comment;

/**
 * commentsテーブルに対してSQLを実行するクラス(DAO)
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Repository
@Transactional
public class CommentsRepository {
	/**
	 * テーブル名
	 */
	private final String TABLE_NAME = "comments";
	@Autowired
	NamedParameterJdbcTemplate template;
	/**
	 * RowMapperの実装
	 * SQLの結果からCommentオブジェクトを生成
	 */
	private static final RowMapper<Comment> commentRowMapper = (rs ,i) ->{
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String content = rs.getString("content");
		Integer articleId = rs.getInt("article_id");
		
		return new Comment(id ,name ,content ,articleId);
	};
	/**
	 * commentsテーブルからカラムを全件取得
	 * @return List＜Comment＞
	 */
	public List<Comment> findAll(){
		String sql = "select id ,name ,content ,article_id from " + TABLE_NAME;
		List<Comment> commentList = template.query(sql, commentRowMapper);
		return commentList;
	}
	/**
	 * 引数のCommentオブジェクトの情報を元にcommentsテーブルに1件insert
	 * @param comment
	 */
	public void insert(Comment comment){
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String sql = "insert into " + TABLE_NAME + "(name ,content ,article_id) values(:name ,:content ,:articleId)";
		template.update(sql, param);
	}
	/**
	 * 引数のidを持つカラムをarticlesテーブルから一件削除
	 * @param id
	 */
	public void deleteOne(Integer id){
		String sql ="delete from "+ TABLE_NAME +" where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	/**
	 * 引数の記事idを持つ全カラムをarticlesテーブルから削除
	 * @param articleId
	 */
	public void deleteCommentsByArticleId(Integer articleId){
		String sql ="delete from "+ TABLE_NAME +" where article_id = :articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
}
