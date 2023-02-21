public class IntNode {
  int value;
  IntNode link;

  public IntNode() {
    value = 0;
    link = null;
  }

  public IntNode(int _data, IntNode _node) {
    value = _data;
    link = _node;
  }

  public void setValue(int v) {
    value = v;
  }

  public void setLink(IntNode next) {
    link = next;
  }

  public int getValue() {
    return value;
  }

  public IntNode getLink() {
    return link;
  }

  public String toString() {
    String stringList = "";
    for (IntNode cursor = this; cursor != null; cursor = cursor.link) {
      stringList += cursor.value + "->";
    }
    stringList += null;
    return stringList;
  }

  public void addNodeAfterThis(int newdata) {
    IntNode next = new IntNode(newdata, link);
    link = next;
  }

  public void removeNodeAfterThis() {
    this.link = link.link;
  }

  public static int listLength(IntNode head) {
    int answer = 0;
    for (IntNode cursor = head; cursor != null; cursor = cursor.link) {
      answer++;
    }
    return answer;
  }

  public static boolean search(IntNode head, int data) {

    for (IntNode cursor = head; cursor != null; cursor = cursor.link) {
      if (cursor.value == data) {
        return true;
      }

    }
    return false;
  }

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

  public static IntNode subtract(IntNode list1, IntNode list2) {
    IntNode substractedHead = list1;
    IntNode prevN;
    for (IntNode cursor2 = list2; cursor2 != null; cursor2 = cursor2.link) {
      prevN = null;
      // System.out.println("round");
      for (IntNode cursor1 = list1; cursor1 != null; cursor1 = cursor1.link) {

        if (cursor1.value == cursor2.value) {
          if (prevN == null) {
            cursor1.link = cursor1.link.link;
            System.out.println(cursor1.value + " got removed");
            break;
          } else {
            prevN.removeNodeAfterThis();
            System.out.println(cursor1.value + "got removed");
            break;
          }
        }
        prevN = cursor1;
        // System.out.println("prev got updated");
      }
    }

    return substractedHead;
  }

  public static void main(String[] args) {
    // test of add afther, to string, lenght.
    IntNode head = new IntNode(12, null);
    head.addNodeAfterThis(11);
    head.link.addNodeAfterThis(25);
    // System.out.println(listLength(head));
    String stringlist = head.toString();
    // System.out.println(stringlist);

    IntNode test = new IntNode(2000, null);
    IntNode curr = test;
    for (int i = 32; i > 0; i--) {
      curr.addNodeAfterThis(i);
      curr = curr.link;
    }
    // System.out.println(test.toString());
    IntNode sortedTest = listSort(test);
    System.out.println(sortedTest.toString());

    IntNode testerase = new IntNode(2000, null);
    IntNode c = testerase;
    for (int i = 78; i > 0; i--) {
      c.addNodeAfterThis(i);
      c = c.link;
    }
    // System.out.println(testerase.toString());

    IntNode result = subtract(testerase, sortedTest);
    IntNode sortresult = listSort(result);
    System.out.println(sortresult.toString());
  }
}