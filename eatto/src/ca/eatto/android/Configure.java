package ca.eatto.android;

/**
 * 存储常量
 * @author pphdsny
 *
 */
public interface Configure {
	
	
	//home fragment tag
	int HOME_LIST_FRAGMENT = 1;
	int HOME_MAP_FRAGMENT = 2;
	int HOME_COLLECT_FRAGMENT = 3;
	//search frahment tag
	int SEARCH_DEFAULT_FRAGMENT = 4;
	int SEARCH_COLLECT_FRAGMENT = 5;
	int SEARCH_HISTORY_FRAGMENT = 6;
	int SEARCH_HOT_FRAGMENT = 7;
	int SEARCH_FOOD_FRAGMENT = 8;
	int SEARCH_RESULT_FRAGMENT = 9;

	//是否处于测试阶段
	boolean isDev = true;
	//保存在sp 中的一些配置文件
	String SP_CONFIGURE = "configure";

}
