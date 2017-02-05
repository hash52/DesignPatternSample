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

import jp.co.rakus.bbs.domain.Article;

/**
 * articlesテーブルに対してSQLを実行するクラス(DAO)
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Repository
@Transactional
public class ArticlesRepository {
	/**
	 * テーブル名
	 */
	private final String TABLE_NAME = "articles";
	@Autowired
	NamedParameterJdbcTemplate template;
	/**
	 * RowMapperの実装
	 * SQLの結果からArticleオブジェクトを生成
	 */
	private static final RowMapper<Article> articleRowMapper = (rs ,i) ->{
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String content = rs.getString("content");
		
		return new Article(id ,name ,content);
	};
	/**
	 * articleテーブルからカラムを全件取得
	 * @return List＜Article＞
	 */
	public List<Article> findAll(){
		String sql = "select id ,name ,content from " + TABLE_NAME;
		List<Article> articleList = template.query(sql, articleRowMapper);
		return articleList;
	}
	/**
	 * 引数のAritcleオブジェクトの情報を元にarticleテーブルに1件insert
	 * @param article
	 */
	public void insert(Article article){
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String sql = "insert into " + TABLE_NAME + "(name ,content) values(:name ,:content)";
		template.update(sql, param);
	}
	
	/**
	 * 引数のidを持つ行をarticleテーブルから削除
	 * @param id
	 */
	public void deleteArticle(Integer id){
		String sql ="delete from "+ TABLE_NAME +" where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
