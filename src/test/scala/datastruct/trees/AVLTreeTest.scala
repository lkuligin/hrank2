package datastruct.trees

import org.scalatest.{MustMatchers, WordSpec}

class AVLTreeTest extends WordSpec with MustMatchers {
  val o = new SelfBalancingTree()

  "AVL tree" in {
    val root = new o.Node(10, 1, null, new o.Node(20, -1, null, null))
    println(o.insert(root, 30))
  }

  "AVL tree 1" in {
    val root = new o.Node(3, 1, new o.Node(2, 0, null, null),
      new o.Node(4, 1, null, new o.Node(5, 0, null, null)))
    println(o.insert(root, 6))
  }
}
