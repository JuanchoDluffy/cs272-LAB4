public class IntNode {
  int value;
  IntNode link;

  /**
   * Constructs a new IntNode with a default value of 0 and null link.
   */
  public IntNode() {
    value = 0;
    link = null;
  }

  /**
   * Constructs a new IntNode with the specified value and link.
   *
   * @param _data the integer value to store in the node.
   * @param _node a reference to the next node in the list.
   */
  public IntNode(int _data, IntNode _node) {
    value = _data;
    link = _node;
  }

  /**
   * Sets the integer value stored in this node.
   *
   * @param v the integer value to store in the node.
   */
  public void setValue(int v) {
    value = v;
  }

  /**
   * Sets the reference to the next node in the list.
   *
   * @param next a reference to the next node in the list.
   */
  public void setLink(IntNode next) {
    link = next;
  }

  /**
   * Returns the integer value stored in this node.
   *
   * @return the integer value stored in this node.
   */
  public int getValue() {
    return value;
  }

  /**
   * Returns a reference to the next node in the list.
   *
   * @return a reference to the next node in the list.
   */
  public IntNode getLink() {
    return link;
  }

  /**
   * Returns a String representation of the list starting at this node.
   *
   * @return a String representation of the list starting at this node.
   */
  public String toString() {
    String stringList = "";
    for (IntNode cursor = this; cursor != null; cursor = cursor.link) {
      stringList += cursor.value + "->";
    }
    stringList += null;
    return stringList;
  }

  /**
   * Adds a new node with the specified integer value immediately after this node.
   *
   * @param newdata the integer value to add to the list.
   * @precondition newdata is not null.
   * @postcondition a new node with the specified integer value is added
   *                immediately after this node.
   */
  public void addNodeAfterThis(int newdata) {
    IntNode next = new IntNode(newdata, link);
    link = next;
  }

  /**
   * Removes the node immediately following this node from the list.
   *
   * @precondition this node is not null and this node has a non-null next node.
   * @postcondition the node immediately following this node is removed from the
   *                list.
   */
  public void removeNodeAfterThis() {
    this.link = link.link;
  }

  /**
   * Returns the number of nodes in the list starting at the specified head node.
   *
   * @param head the head node of the list.
   * @return the number of nodes in the list starting at the specified head node.
   */
  public static int listLength(IntNode head) {
    int answer = 0;
    for (IntNode cursor = head; cursor != null; cursor = cursor.link) {
      answer++;
    }
    return answer;
  }

  /**
   * Searches a linked list of integers for a specified value.
   *
   * @param head the head node of the linked list to search
   * @param data the integer value to search for
   * 
   * @precondition head is not null
   * 
   * 
   * @return true if the value is found in the linked list, false otherwise
   * 
   * @postcondition the state of the linked list pointed to by head is not
   *                modified
   */
  public static boolean search(IntNode head, int data) {

    for (IntNode cursor = head; cursor != null; cursor = cursor.link) {
      if (cursor.value == data) {
        return true;
      }

    }
    return false;
  }

  /**
   * 
   * Sorts the linked list in non-descending order and returns the head of the
   * sorted list.
   * 
   * @param head the head node of the linked list to be sorted
   * @return the head node of the sorted linked list, or null if the input list is
   *         empty
   * @postcondition the linked list is sorted in non-descending order
   */
  public static IntNode listSort(IntNode head) {
    IntNode highNode = head;
    IntNode before = null;
    IntNode current = head;
    IntNode sortedHead = null;
    IntNode beforeHihgIntNode = null;
    // if list is empty or contains only 1 item
    if (head == null || head.link == null) {
      return head;
    }
    while (head != null) {
      current = head;
      highNode = head;
      beforeHihgIntNode = null;
      while (current != null) {
        if (current.value > highNode.value) {
          highNode = current;
          beforeHihgIntNode = before;
        }
        before = current;
        current = current.link;
      }
      if (beforeHihgIntNode == null) {
        head = head.link;
        highNode.link = sortedHead;
        sortedHead = highNode;
      } else {
        beforeHihgIntNode.link = highNode.link;
        highNode.link = sortedHead;
        sortedHead = highNode;
      }
    }
    return sortedHead;
  }

  /**
   * Removes all nodes from list1 that have a value matching a node in list2.
   *
   * @param list1 the first linked list
   * @param list2 the second linked list
   * @return the head node of the resulting linked list
   *
   * @precondition both list1 and list2 are not null
   * @postcondition the resulting linked list does not contain any nodes with a
   *                value
   *                that exists in list2
   */
  public static IntNode subtract(IntNode list1, IntNode list2) {
    IntNode substractedHead = list1;
    IntNode prevN;
    for (IntNode cursor2 = list2; cursor2 != null; cursor2 = cursor2.link) {
      prevN = null;
      for (IntNode cursor1 = list1; cursor1 != null; cursor1 = cursor1.link) {

        if (cursor1.value == cursor2.value) {
          if (prevN == null) {
            cursor1.link = cursor1.link.link;
            break;
          } else {
            prevN.removeNodeAfterThis();
            break;
          }
        }
        prevN = cursor1;

      }
    }
    return substractedHead;
  }

  public static void main(String[] args) {

    IntNode test = new IntNode(1, null);
    IntNode curr = test;
    for (int i = 32; i > 0; i--) {
      curr.addNodeAfterThis(i);
    }

    IntNode sortedTest = listSort(test);

    IntNode testErase = new IntNode(0, null);
    IntNode c = testErase;
    for (int i = 45; i > 0; i--) {
      c.addNodeAfterThis(i);
    }
    System.out.println(sortedTest.toString());

    System.out.println(testErase);

    IntNode result = subtract(testErase, sortedTest);
    IntNode sortresult = listSort(result);
    // should not contain numbres from 1 to 32
    System.out.println(sortresult.toString());

    IntNode list1 = new IntNode(1, new IntNode(2, new IntNode(3, new IntNode(3, null))));
    IntNode list2 = new IntNode(2, new IntNode(4, new IntNode(3, null)));
    IntNode resultt = subtract(list1, list2);
    System.out.println(resultt); // should output "1 -> 3 -> null"

    IntNode list3 = new IntNode(1, new IntNode(2, new IntNode(3, null)));
    IntNode list4 = new IntNode(2, new IntNode(2, new IntNode(4, null)));
    IntNode result2 = subtract(list3, list4);
    System.out.println(result2); // should output "1 -> 3 -> null"

  }
}