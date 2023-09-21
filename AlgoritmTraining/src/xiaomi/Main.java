package xiaomi;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {
    //根据输入的字符串形式构建二叉树
    //从读取字符串的第一个字符开始为根节点
    //若读到左括号，则下一个数字为当前节点的左孩子
    //若读到逗号，则下一个数字为当前节点的兄弟节点
    //若读到右括号，则当前指针回退到父亲节点
    //若字符串已解析完毕，则二叉树构建完毕
    //Treenode{
    // int value;
    // Treenode leftChild;
    // Treenode rightChild;
    // Treenode sibling;
    // Treenode parent;
    //}
    //中序遍历这个二叉树
    static String solution(String input) {

        class TreeNode {
            TreeNode(int a){
                value = a;
            }
            int value;
            TreeNode leftChild;
            TreeNode rightChild;
            TreeNode sibling;
            TreeNode parent;

            public  String traverse(TreeNode begin){
                StringBuffer s = new StringBuffer();
                if(begin.leftChild != null){
                    traverse(begin.leftChild);
                }
                s.append(begin.value);
                if(begin.rightChild != null){
                    traverse(begin.rightChild);
                }
                return s.toString();
            }
        }
        String result = null;
        //构造二叉树
        int length =input.length();
        //确定根节点
        TreeNode root = new TreeNode(Integer.parseInt(input,1));
        //g构造二叉树
        TreeNode begin = root;
        for(int i = 1 ; i < length ; i++){
            char nowChar = input.charAt(0);
            if(nowChar == '('){
                i++;
                input = input.substring(1);
                if(input.charAt(0) <= '9' && input.charAt(0) >= '0'){
                    root.leftChild = new TreeNode(Integer.parseInt(input,1));
                    root.leftChild.parent = root;
                    root = root.leftChild;
                }
            }
            if(nowChar == ','){
                i++;
                input = input.substring(1);
                if(input.charAt(0) <= '9' && input.charAt(0) >= '0'){
                    root.sibling = new TreeNode(Integer.parseInt(input,1));
                    root.sibling.parent = root.parent;
                    root = root.sibling;
                }
            }
            if(nowChar == ')'){
                i++;
                input = input.substring(1);
                root = root.parent;
            }
        }

        //中序遍历这个二叉树
        result = begin.traverse(begin);
        return result;

    }

    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String res;

        String _input;
        try {
            _input = in.nextLine();
        } catch (Exception e) {
            _input = null;
        }

        res = solution(_input);
        System.out.println(res);
    }
}
