package Vassal40k.Army.Loader;

import Vassal40k.Army.Loader.Converters.Box;
import java.util.Collections;
import java.util.List;

public class ModelPackager
{
    private Node root;
    private int maxWidth = 500;
    
    private ModelPackager() {}; //We don't want others constructing these
    
    public static void Pack(List<? extends Box> boxes)
    {
        Pack(boxes, true);
    }
    public static void Pack(List<? extends Box> boxes, boolean useOffset)
    {
        ModelPackager packager = new ModelPackager();
        packager.Fit(boxes, useOffset);
    }
    
    
    protected void Fit(List<? extends Box> boxes, boolean useOffset)
    {
        try
        {
            Collections.sort(boxes, new SizeComparatorDescending());
            
            int offsetX = 0;
            int offsetY = 0;
            if (useOffset)
            {
                offsetX = boxes.get(0).getWidth() / 2 + 20;
                offsetY = boxes.get(0).getHeight() / 2 + 20;
            }
            maxWidth = 500 - offsetX;
            
            root = new Node(0, 0, boxes.get(0).getWidth(), boxes.get(0).getHeight());

            for (Box box : boxes)
            {
                Node node = findNode(root, box.getWidth(), box.getHeight());
                if (node != null)
                {
                    Node fit = splitNode(node, box.getWidth(), box.getHeight());
                    box.setX(fit.x);
                    box.setY(fit.y);
                }
                else
                {
                    Node fit = growNode(box.getWidth(), box.getHeight());
                    box.setX(fit.x);
                    box.setY(fit.y);
                }
            }
            
            for (Box box : boxes)
            {
                box.setX(box.getX() + offsetX);
                box.setY(box.getY() + offsetY);
            }
        }
        catch(Exception ex)
        {
        }
    }
    
    protected Node findNode(Node node, int width, int height)
    {
        if (node.used)
        {
            Node result = findNode(node.right, width, height);
            if (result != null)
                return result;
            result = findNode(node.down, width, height);
            if (result != null)
                return result;
            else
                return null;
        }
        else if (width <= node.w && height <= node.h)
            return node;
        else
            return null;
    }
    
    protected Node splitNode(Node node, int w, int h)
    {
        node.used = true;
        node.down = new Node(node.x, node.y + h, node.w, node.h - h);
        node.right = new Node(node.x + w, node.y, node.w - w, h);
        return node;
    }
    
    protected Node growNode(int w, int h)
    {
        boolean canGrowDown = (w <= root.w);
        boolean canGrowRight = (h <= root.h);// && (root.w + w < maxWidth);
        
        boolean shouldGrowRight = canGrowRight && (root.h + h >= (root.w + w));
        boolean shouldGrowDown = canGrowDown && (root.w + w >= (root.h + h));
        
        if (shouldGrowRight)
            return growRight(w, h);
        else if (shouldGrowDown)
            return growDown(w, h);
        else if (canGrowRight)
            return growRight(w, h);
        else if (canGrowDown)
            return growDown(w, h);
        else
            return null;
    }
    
    protected Node growRight(int w, int h)
    {
        Node oldRoot = root;
        root = new Node(0, 0, oldRoot.w + w, oldRoot.h);
        root.used = true;
        root.down = oldRoot;
        root.right = new Node(oldRoot.w, 0, w, oldRoot.h);
        Node node = findNode(root, w, h);
        if (node != null)
            return splitNode(node, w, h);
        else
            return null;
    }
    
    protected Node growDown(int w, int h)
    {
        Node oldRoot = root;
        root = new Node(0, 0, oldRoot.w, oldRoot.h + h);
        root.used = true;
        root.down = new Node(0, oldRoot.h, oldRoot.w, h);
        root.right = oldRoot;
        Node node = findNode(root, w, h);
        if (node != null)
            return splitNode(node, w, h);
        else
            return null;
    }
    
    protected class Node
    {
        public Node down = null;
        public Node right = null;
        public boolean used = false;
        public int x = 0;
        public int y = 0;
        public int w = 0;
        public int h = 0;
        
        public Node(int _x, int _y, int _w, int _h)
        {
            x = _x;
            y = _y;
            w = _w;
            h = _h;
        }
    }
}