package model;

/**
 * @author Shinrai
 * @since 2017-6-16 17:11:59
 * 回调接口类
 */
public interface WindowCallBack {
	public void nextStep(); //进入下一个操作
	public void textAreaAppend(String s); //文本框追加文本
}
