import java.util.ArrayList;

public class binary_tree {
    public interface position<String>{ String getelement();}
    protected static class  Node<String>implements position<String>{
        public Node(String e,Node<String> par,Node<String> lef,Node<String> rig)
        {
            element =e;
            parent =par;
            left=lef;
            right=rig;

        }
        
        public String element;     //store element 
        public Node<String> parent;    //store parent referance
        public Node<String> left;      //store left referance
        public Node<String> right ;    //store right referance 


        public void setelement(String e)         {element =e;}       //set element
        public void setparent(Node<String> p)    {  parent=p; }      //set parent 
        public void setleft(Node<String> p)      {  left=p;   }      //set left child 
        public void setrighht(Node<String> p)    {  right=p;  }      //set right child 

        public String getelement()      { return element;}      //return element stored 
        public Node<String> getparent() { return parent;}        //return referance of parent
        public Node<String> getleft()   { return left; }         //return referance of left child 
        public Node<String> getright()   { return right;}        //return referance of right child 


        
    }
    
    private Node<String> root=new Node<String>(null, null, null, null);   //root pointer to acces starting of tree
         public int size;                              //variable to store number of nodes present in tree 
    
    public Node<String> createnode(String e,Node<String> p,Node<String> l,Node<String> r)    //creating new node 
    {   return new Node<String>(e, p, l, r);}

    
    // //override methods of tree 
    public boolean isexternal(position<String> p){  return numchildren(p)==0;   }   
    public boolean isinternal(position<String> p){ return numchildren(p)>0;};
    public boolean isroot(position<String> p) {  return root()==p;}
    public boolean isemepty(position<String> p) {return size()==0;}
    
    
    
    public int numchildren(position<String> p) {
        int count=0;         //variable to store count of children
        if(left(p)!=null)    
        ++count;             //count increase if left child exist
        if(right(p)!=null)   
        ++count;            //count increase if right child exist
        return count;
    }

    
    public position<String> subling(position<String> p) {
        position<String> parent=parent(p);              
        if(parent==null) //cheking passed position is root  if root than no subling                     
        return null;
        else if(left(parent)==p)      //given left than return right child 
        return right(parent);         //given right than return left child 
        else 
        return left(parent);
    }

    
    public Iterable<position<String>> children(position<String> p) {
        ArrayList<position<String>> snapshot= new ArrayList(2);   //list to store child of p
        if(left(p)!=null)
        snapshot.add(left(p));           //if left child exist than added in list
        if(right(p)!=null)
        snapshot.add(left(p));        //if right child exist than added in list 
        return snapshot;
    }
    
    
    
    
    
    
    
    
    
    public binary_tree(){ size=0;};                  //constructor creates object of linked_binary_tree


    public Node<String> validate(position<String> p)
    {
        if(!(p instanceof Node))                                    
        throw new IllegalArgumentException("Not valide position ");
        Node<String> node=(Node<String>)p;
        if(node.getparent()==node)
        throw new IllegalArgumentException("p is no longer in tree ");
        return node;
        
    }
    

    public position<String> root()                //return position of root 
    { return  root; }          //type cast because we required position but root is node 
    
    public int size()                       //return number of nodes present in tree 
    {   return size;    }

    public position<String> parent(position<String> p) {
        Node<String> node=validate(p);
        return node.getparent();
    }

    public boolean isemepty() 
    { return size==0; }


    public position<String> left(position<String> p) {
        Node<String> node=validate(p);
        return (position<String>) node.getleft() ;   //if left child exit than return its position otherwise null
        
    }
    
    public position<String> right(position<String> p) {
        Node<String> node=validate(p);
        return (position<String>)node.getright();
    }

    public position<String> addroot(String e)
    {
        if(!isemepty())
        throw new IllegalArgumentException(" root is already exit .");
        root.setelement(e);
        return (position<String>) root;
    }

    public position<String> addleft(position<String> p,String e)
    {
        Node<String> parentnode= validate(p);
        /*if(parentnode.getleft()!=null)
        {  System.out.println(parentnode.getleft());
        throw new IllegalArgumentException("left child already exit");
        }*/
        Node<String> child=createnode(e, parentnode, null,null);
        parentnode.setleft(child);
        return (position<String>) child; 
    }

    public position<String> addright(position<String> p,String e)
    {
        Node<String> parentnode= validate(p);
        if(parentnode.getright()!=null)
        throw new IllegalArgumentException("right child already exit");
        Node<String> child=createnode(e, null, parentnode,null);
        parentnode.setrighht(child);
        return (position<String>) child; 
    }
    public String set(position<String> p,String e)
    {
        Node<String> node=validate(p);
        String temp=node.getelement();
        node.setelement(e);
        return temp;
    }

    public String remove(position<String> p)
    {
        Node<String> node=validate(p);
        Node<String> parent=node.getparent();
        if(numchildren(p)==2)
        throw new IllegalArgumentException("removing not possible because p has two child .");
        Node<String> child=(node.getleft()!=null ? node.getleft() : node.getright());
        child.setparent(parent);
        if(parent.getleft()==p)
          parent.setleft(child);
        else
          parent.setrighht(child);
        String temp=node.getelement();
        node.setelement(null);
        node.setleft(null);
        node.setrighht(null);
        node.setparent(null);
        return temp;
        
         
    }

    public void attach(position<String> p,binary_tree t1,binary_tree t2)
    {
        if(!isexternal(p))
        throw new IllegalArgumentException("p is not leaf ");
        Node<String> node=validate(p);
        Node<String> t1node=validate(t1.root());
        Node<String> t2node=validate(t2.root());
        t1node.setparent(node);
        t2node.setparent(node);
        node.setleft(t1node);
        node.setrighht(t2node);
        t1node.setelement(null);
        t2node.setleft(null);
        t1node.setleft(null);
        t1node.setrighht(null);
        t1node.setparent(null);
        t2node.setelement(null);
        t2node.setrighht(null);
        t2node.setparent(null);    
    }
    public static Boolean isNumric(String s)
    {
        if(s==null)
        return false;
        try{
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException e){
            return false;

        }
    }
    public  void eulertour(binary_tree T, position<String> p )
    {   
        if(left(p)!=null)
        {   System.out.print(" ( ");
            eulertour(T, left(p));
            
        }
        else
        System.out.print(p.getelement()+" ");
        System.out.print(parent(p).getelement()+" ");
        position<String> right=right(parent(p));
        if(parent(p)!=T.root())
        {
            if(!isNumric(right.getelement()))
            { eulertour(T, p);}
            else
            {System.out.print(p.getelement()+" ) ");}
            
        }
        else
        {
            if(!isNumric(right(p).getelement()))
            { eulertour(T, right(p));}
            else
            System.out.print(right(p).getelement()+" ) ");
        }
        
        
      
        

    }    
    
    public static void main(String[] args) {
        binary_tree bt=new binary_tree();
       // bt.remove(bt.root());
        position<String> root =bt.addroot("-");  //adding root // 1
        position<String> r_l1=bt.addleft(root,"/");  //done  2
        position<String> r_r1=bt.addright(root,"+");  //done  3
        position<String> r_l1_l1=bt.addleft(r_l1,"*");//done 4
        position<String> r_l1_r1=bt.addright(r_l1,"+");//done 5
        position<String> r_r1_l1=bt.addleft(r_r1,"*");//done 6
        position<String> r_r1_r1=bt.addright(r_r1,"6");//done 7
        position<String> r_l1_l1_l1=bt.addleft(r_l1_l1, "+");//done 8
        position<String> r_l1_l1_r1=bt.addright(r_l1_l1, "3");//done9
        position<String> r_l1_r1_l1=bt.addleft(r_l1_r1,"-");//done 10
        position<String> r_l1_r1_r1=bt.addright(r_l1_r1,"2");//d 11
        position<String> r_r1_l1_l1=bt.addleft(r_r1_l1,"3");//d12
        position<String> r_r1_l1_r1=bt.addright(r_r1_l1,"-");//13
        position<String> r_l1_l1_l1_l1=bt.addleft(r_l1_l1_l1, "3");//14
        position<String> r_l1_l1_l1_r1=bt.addright(r_l1_l1_l1, "1");//15
        position<String> r_l1_r1_l1_l1=bt.addleft(r_l1_r1_l1,"9");//16
        position<String> r_l1_r1_l1_r1=bt.addright(r_l1_r1_l1,"5");//17
        position<String> r_r1_l1_r1_l1=bt.addleft(r_r1_l1_r1,"7");//18
        position<String> r_r1_l1_r1_r1=bt.addright(r_r1_l1_r1,"4");//19
        
        
        bt.eulertour(bt,bt.root());
    
    
    }


    

}
