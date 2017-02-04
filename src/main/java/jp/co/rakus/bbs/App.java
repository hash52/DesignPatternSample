package jp.co.rakus.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 実行クラス
 * ここを実行してブラウザでhttp://localhost:8080/bbs/view
 * @author HirokiHashi
 * 所属：株式会社ラクス
 * 作成日:2016.11.14
 *
 */
@SpringBootApplication
public class App 
{
	/**
	 * メインメソッド
	 */
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}

