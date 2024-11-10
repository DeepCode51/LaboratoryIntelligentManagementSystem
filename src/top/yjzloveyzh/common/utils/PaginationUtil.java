/**
 *
 */
package top.yjzloveyzh.common.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import top.yjzloveyzh.common.Constants;

/**
 * @author 171882105@qq.com
 *
 */
public class PaginationUtil {

    public static List<Integer> makePageIndexList(int currentPageIndex, int perPageDateCount, int totalPageCount) {

        int maxPageListLength = 7;
        int maxPages = (int) Math.ceil(totalPageCount * 1.0 / perPageDateCount);

        try {
            maxPageListLength = Integer.parseInt(PropertyUtil.getProperty(Constants.Properties.PROPERTIES_KEY_PAGINATION_CHOOSE_LIST));
        } catch (NumberFormatException numberFormatException) {

        }

        LinkedList<Integer> pageLinkedList = new LinkedList<Integer>();

        if (currentPageIndex > totalPageCount || currentPageIndex <= 0) {

            return new ArrayList<Integer>(pageLinkedList);
        } else if(totalPageCount <= 7) {

            for (int i = 0; i < totalPageCount; i++) {
                pageLinkedList.add(i + 1);
            }

            return new ArrayList<Integer>(pageLinkedList);
        } else {
            pageLinkedList.add(currentPageIndex);
            int left = currentPageIndex - 1;
            int right = currentPageIndex + 1;

            while (pageLinkedList.size() < maxPageListLength) {

                if (pageLinkedList.size() < maxPageListLength && left > 0) {
                    pageLinkedList.addFirst(left--);
                }

                if (pageLinkedList.size() < maxPageListLength && right <= totalPageCount) {
                    pageLinkedList.addLast(right++);
                }
            }

            return new ArrayList<Integer>(pageLinkedList);
        }
    }
}
