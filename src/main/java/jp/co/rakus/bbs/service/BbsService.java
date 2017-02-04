package jp.co.rakus.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.bbs.domain.Article;
import jp.co.rakus.bbs.domain.Comment;
import jp.co.rakus.bbs.repository.ArticlesRepository;
import jp.co.rakus.bbs.repository.CommentsRepository;
/**
 * DAOを呼び出すクラス
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日：2016.11.14
 *
 */
@Service
public class BbsService {
	@Autowired
	ArticlesRepository articlesRepository;
	@Autowired
	CommentsRepository commentsRepository;
	

	/**
	 * articlesテーブルから全件取得してArticleオブジェクトを格納したリストを返す
	 * @return List＜Article＞
	 */
	public List<Article> findAllArticle(){
		return articlesRepository.findAll();
		//comment.findAll();
	}
	
	/**
	 * 渡されたArticleオブジェクトの情報をDAOに渡して記事の書き込みを行う
	 * @param article
	 */
	public void insertArticle(Article article){
		articlesRepository.insert(article);
	}
	
	/**
	 * 引数の記事idを持つ記事とそれに付随するコメントを削除する
	 * @param id 記事id
	 */
	public void deleteArticle(Integer id){
		//commentsテーブルのarticle_idは外部キー。articlesテーブルに依存しているのでcommentsテーブルのカラムを先に削除する。
		commentsRepository.deleteCommentsByArticleId(id);
		articlesRepository.deleteArticle(id);
	}
	
	/**
	 * commentsテーブルから全件取得してCommentオブジェクトを格納したリストを返す
	 * @return List＜Comment＞
	 */
	public List<Comment> findAllComments(){
		return commentsRepository.findAll();
	}
	/**
	 * 渡されたCommentオブジェクトの情報をDAOに渡してコメントの書き込みを行う
	 * @param comment
	 */
	public void insertComment(Comment comment){
		commentsRepository.insert(comment);
	}
	/**
	 * 渡されたコメントidを持つコメント1件を削除する
	 * @param id コメントid
	 */
	public void deleteOneComment(Integer id){
		commentsRepository.deleteOne(id);
	}
	

}
