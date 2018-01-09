package datastructure.tree.rbTree;

import datastructure.tree.rbTree.RBTree;

/**
 * Java 语言: 二叉查找树
 *
 * @author skywang
 * @date 2013/11/07
 */
public class Test {

    private static final int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
    private static final boolean mDebugInsert = true;    // "插入"动作的检测开关(false，关闭；true，打开)
    private static final boolean mDebugDelete = false;    // "删除"动作的检测开关(false，关闭；true，打开)

    public static void main(String[] args) {
        int i, ilen = a.length;
        RBTree<Integer> tree=new RBTree<Integer>();

        System.out.printf("== original tree: ");
        for(i=0; i<ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        for(i=0; i<ilen; i++) {
            tree.insert(a[i]);
            // mDebugInsert=true: to test insert functions
            if (mDebugInsert) {
                System.out.printf("== add node: %d\n", a[i]);
                System.out.printf("== tree info: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

//        System.out.printf("== preOrder: ");
//        tree.preOrder();
//
//        System.out.printf("\n== inOrder: ");
//        tree.inOrder();
//
//        System.out.printf("\n== postOrder: ");
//        tree.postOrder();
//        System.out.printf("\n");
//
//        System.out.printf("== minimum: %s\n", tree.minimum());
//        System.out.printf("== maximum: %s\n", tree.maximum());
//        System.out.printf("== tree info: \n");
//        tree.print();
//        System.out.printf("\n");
//
//        // mDebugDelete=true: to test remove functions
//        if (mDebugDelete) {
//            for(i=0; i<ilen; i++)
//            {
//                tree.remove(a[i]);
//
//                System.out.printf("== 删除节点: %d\n", a[i]);
//                System.out.printf("== 树的详细信息: \n");
//                tree.print();
//                System.out.printf("\n");
//            }
//        }
//
//        // remove tree
//        tree.clear();
    }
}