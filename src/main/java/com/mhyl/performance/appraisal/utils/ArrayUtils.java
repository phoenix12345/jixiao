package com.mhyl.performance.appraisal.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ArchieDing
 * @since 2020/09/03
 */
public class ArrayUtils {
	private ArrayUtils() {
	}

	@SafeVarargs
	public static <T> boolean isEmpty(final T... array) {
		return array == null || array.length == 0;
	}

	public static boolean isArray(Object value) {
		return null != value && value.getClass().isArray();
	}

	public static <T> T find(List<T> list, Filter<T> filter) {
		if (list == null || list.size() == 0) {
			return null;
		}

		for (T t : list) {
			if (filter.when(t)) {
				return t;
			}
		}
		return null;
	}

	public static <T> int indexOf(List<T> list, T t) {
		return list.indexOf(t);
	}

	public static <T> int indexOf(List<T> list, Filter<T> filter) {
		if (list == null || list.size() == 0) {
			return -1;
		}

		int i = 0;
		for (T t : list) {
			if (filter.when(t)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static <T> boolean contains(List<T> list, T t) {
		return list.contains(t);
	}

	public static <T> boolean contains(List<T> list, Filter<T> filter) {
		return find(list, filter) != null;
	}

	public static <T> boolean addAll(List<T> afterList, List<T> addList) {
		return afterList.addAll(addList);
	}

	public static <T> boolean removeAll(List<T> afterList, List<T> removeList) {
		return afterList.removeAll(removeList);
	}

	public static <T> boolean removeAll(List<T> afterList, List<T> removeList, Equals<T> equals) {
		List<T> needRemoveList = new ArrayList<>();
		for (T afterItem : afterList) {
			for (T removeItem : removeList) {
				if (equals.when(afterItem, removeItem)) {
					needRemoveList.add(afterItem);
					break;
				}
			}
		}
		return afterList.removeAll(needRemoveList);
	}

	public static <T> List<T> removeDuplicate(List<T> list, Equals<T> equals) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (equals.when(list.get(i), list.get(j))) {
					list.remove(j);
				}
			}
		}
		return list;
	}

	public static <T> List<T> removeAllByClone(List<T> allList, List<T> removeList, Equals<T> equals) {
		List<T> cloneList = clone(allList);
		List<T> needRemoveList = new ArrayList<>();
		for (T cloneItem : cloneList) {
			for (T removeItem : removeList) {
				if (equals.when(cloneItem, removeItem)) {
					needRemoveList.add(cloneItem);
					break;
				}
			}
		}
		cloneList.removeAll(needRemoveList);
		return cloneList;
	}

	public static <T> List<T> clone(List<T> list) {
		List<T> newList = new ArrayList<>();
		if (list == null || list.size() == 0) {
			return newList;
		}
		newList.addAll(list);
		return newList;
	}

	public interface Filter<T> {
		/**
		 * 过滤信息
		 *
		 * @param item
		 * @return
		 */
		boolean when(T item);
	}

	public interface Equals<T> {
		/**
		 * 判断相等
		 *
		 * @param item1
		 * @param item2
		 * @return
		 */
		boolean when(T item1, T item2);
	}
}
