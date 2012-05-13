data ListTree = ListNode ListTree ListTree [Integer]
              | ListLeaf [Integer]

data IntTree = IntNode IntTree IntTree Integer
             | IntLeaf Integer

