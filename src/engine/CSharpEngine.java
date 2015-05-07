/**
 * ソースのステップ数、コメント行、空行をカウントするエンジンです。
 *
 */
package engine;

import java.io.*;

public class CSharpEngine implements Engineable{
/* ---------------------------------------------------------
 メイン
--------------------------------------------------------- */
	public static void main(String[] args) throws Exception{
		File src = new File("t.java");
		Engineable engine = new JavaEngine(src);
		engine.parse();
		System.out.println("総行数:" + engine.getLinage());
		System.out.println("ステップ:" + engine.getStep());
		System.out.println("コメント:" + engine.getComment());
		System.out.println("空:" + engine.getEmpty());
	}
/* ---------------------------------------------------------
 定数
--------------------------------------------------------- */
	/** 単一コメント */
	public static final String c_single = "//";
	/** コメント開始 */
	public static final String c_doc_s = "/*";
	/** コメント終了 */
	public static final String c_doc_e = "*/";

	/** { */
	public static final String c_k_s = "{";
	/** } */
	public static final String c_k_e = "}";

/* ---------------------------------------------------------
 変数
--------------------------------------------------------- */
	/** Javaソースファイル */
	private File src;

	/** 総行数 */
	private int linage;
	/** コメントフラグ */
	private boolean c_flag;
	/** ステップ数 */
	private int step;
	/** コメント数 */
	private int comment;
	/** 空行数 */
	private int empty;

/* ---------------------------------------------------------
 コンストラクタ
--------------------------------------------------------- */
	/**
	 * 解析するファイルを指定して CSharpEngine インスタンスを生成します。
	 *
	 * @param src		C#ソースファイル
	 */
	public CSharpEngine(File src){
		this.src = src;
	}
/* ---------------------------------------------------------
 解析
--------------------------------------------------------- */
	/**
	 * 解析します。
	 *
	 * @throws Exception	解析中にエラーが発生した場合
	 */
	public void parse() throws Exception{
		int single;
		int k;

		BufferedReader br = new BufferedReader(new FileReader(src));
		String line = null;
		while((line = br.readLine()) != null){
			line = line.trim();
//			line = new String(line.trim().getBytes("SJIS"), "UTF-8");


			// 総行数
			linage++;

			// コメント継続判断
			if(c_flag){
				comment++;
				if(line.indexOf(c_doc_e) != -1){
					c_flag = false;
				}
				continue;
			}
			// docコメント行
			if(line.indexOf(c_doc_s) != -1){
				c_flag = true;
				if(line.indexOf(c_doc_e) != -1){
					c_flag = false;
				}
				comment++;
				continue;
			}
			// 単一コメント
			if((single = line.indexOf(c_single)) != -1){
				if(single == 0){
					comment++;
					continue;
				}
			}

			// {
			//if((k = line.indexOf(c_k_s)) != -1){
			//	if(k == 0){
			//		continue;
			//	}
			//}
			// }
			//if((k = line.indexOf(c_k_e)) != -1){
			//	if(k == 0){
			//		continue;
			//	}
			//}

			// 空行
			if(line == null || line.length() == 0){
				empty++;
				continue;
			}
			// ステップ行
			step++;
		}
	}
/* ---------------------------------------------------------
 総行数
--------------------------------------------------------- */
	/**
	 * 総行数を取得します。
	 *
	 * @return 総行数
	 */
	public int getLinage(){
		return linage;
	}
/* ---------------------------------------------------------
 ステップ数取得
--------------------------------------------------------- */
	/**
	 * ステップ数を取得します。
	 *
	 * @return ステップ数
	 */
	public int getStep(){
		return step;
	}
/* ---------------------------------------------------------
 コメント行取得
--------------------------------------------------------- */
	/**
	 * コメント行取得
	 *
	 * @return コメント行
	 */
	public int getComment(){
		return comment;
	}
/* ---------------------------------------------------------
 空行取得
--------------------------------------------------------- */
	/**
	 * 空行取得
	 *
	 * @return 空行
	 */
	public int getEmpty(){
		return empty;
	}
/* ---------------------------------------------------------
 printメソッド取得
--------------------------------------------------------- */
	/**
	 * デバッグ用 System.out.printメソッド取得
	 *
	 * @return System.out.printメソッド
	 */
	public int getPrint(){
		return 0;
	}
/* ---------------------------------------------------------
 printStackTraceメソッド取得
--------------------------------------------------------- */
	/**
	 * デバッグ用 printStackTraceメソッド取得
	 *
	 * @return printStackTraceメソッド
	 */
	public int getTrace(){
		return 0;
	}
/* ---------------------------------------------------------
 変更取得
--------------------------------------------------------- */
	/**
	 * 変更取得
	 *
	 * @return 変更行
	 */
	public int getChange(){
		return 0;
	}

}
