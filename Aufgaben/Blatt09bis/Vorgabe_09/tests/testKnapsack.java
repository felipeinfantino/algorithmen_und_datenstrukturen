import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

public class testKnapsack {

    private Knapsack knapsackGlo;
    private KnapsackSolver knapsackSolverGlo;
    private LinkedList<Item> itemLinkedListGlo;

    @Before
    public void set(){

        knapsackGlo = new Knapsack(28);
        knapsackSolverGlo = new KnapsackSolver();
        itemLinkedListGlo= new LinkedList<>();

        Item item1 = new Item(1, 4, 10);
        Item item2 = new Item(2, 5, 15);
        Item item3 = new Item(3, 2, 5);
        Item item4 = new Item(3, 4, 10);
        Item item5 = new Item(3, 7, 15);
        Item item6 = new Item(3, 3, 20);
        Item item7 = new Item(3, 1, 5);

        itemLinkedListGlo.add(item1);
        itemLinkedListGlo.add(item2);
        itemLinkedListGlo.add(item3);
        itemLinkedListGlo.add(item4);
        itemLinkedListGlo.add(item5);
        itemLinkedListGlo.add(item6);
        itemLinkedListGlo.add(item7);
    }

@Test
public void testWith3El(){

    Knapsack k = new Knapsack(10);
    KnapsackSolver knapsackSolver = new KnapsackSolver();
    LinkedList<Item> itemLinkedList = new LinkedList<>();

    Item item1 = new Item(1, 4, 10);
    Item item2 = new Item(2, 7, 15);
    Item item3 = new Item(3, 2, 6);

    itemLinkedList.add(item1);
    itemLinkedList.add(item2);
    itemLinkedList.add(item3);

    k = knapsackSolver.solveKnapsackOptimal(k, itemLinkedList);

}

@Test
public void testTakeAll(){

    Knapsack k = new Knapsack(28);
    KnapsackSolver knapsackSolver = new KnapsackSolver();
    LinkedList<Item> itemLinkedList = new LinkedList<>();

    Item item1 = new Item(1, 4, 10);
    Item item2 = new Item(2, 7, 15);
    Item item3 = new Item(3, 2, 5);
    Item item4 = new Item(3, 4, 10);
    Item item5 = new Item(3, 7, 15);
    Item item6 = new Item(3, 3, 20);
    Item item7 = new Item(3, 1, 5);

    itemLinkedList.add(item1);
    itemLinkedList.add(item2);
    itemLinkedList.add(item3);
    itemLinkedList.add(item4);
    itemLinkedList.add(item5);
    itemLinkedList.add(item6);
    itemLinkedList.add(item7);

    k = knapsackSolver.solveKnapsackOptimal(k, itemLinkedList);

    System.out.println(k.toString());
    Assert.assertEquals("The value is not the maximal", k.currentValue, 80);
    Assert.assertEquals("All the elements are not in the list, even though there is enough place", k.getCurrentWeight(), 28);

}

@Test
public void testEmptyKnapsack(){

    Knapsack k = new Knapsack(10);
    KnapsackSolver knapsackSolver = new KnapsackSolver();
    LinkedList<Item> itemLinkedList = new LinkedList<>();

    Item item1 = new Item(1, 40, 10);
    Item item2 = new Item(2, 70, 15);
    Item item3 = new Item(3, 20, 5);
    Item item4 = new Item(3, 40, 10);
    Item item5 = new Item(3, 70, 15);

    itemLinkedList.add(item1);
    itemLinkedList.add(item2);
    itemLinkedList.add(item3);
    itemLinkedList.add(item4);
    itemLinkedList.add(item5);

    k = knapsackSolver.solveKnapsackOptimal(k, itemLinkedList);


    Assert.assertTrue("Knapscak includes something, eventhough it isn't that big", k.currentValue == 0);
    Assert.assertTrue(k.getCurrentWeight() == 0);

}


@Test
public void testReachToTheSky(){

    //Knapsack k = new Knapsack(10);
    KnapsackSolver knapsackSolver = new KnapsackSolver();
    LinkedList<Item> itemLinkedList = new LinkedList<>();
    Item temp;
    Random random = new Random();


    int currentWeight = 0, numOfItems = 10, maxValueOfItem = 100;

    // To weight +1, because the randGenerator can give zero values and that's nonsense! To prevent this, the range is upgraded 1-11
    for (int i = 0; i < numOfItems; i++) {
        temp = new Item(i, random.nextInt(10)+1, random.nextInt(maxValueOfItem));
        itemLinkedList.add(temp);
        currentWeight += temp.getWeight();
    }

    Knapsack k = new Knapsack(currentWeight);

    knapsackSolver.solveKnapsackOptimal(k, itemLinkedList);


    System.out.println(k.currentWeight);
    
    Assert.assertTrue("Knap doesn't have everything that it can have", k.getCurrentWeight() == currentWeight);
    Assert.assertTrue("That much of value is too much", k.currentValue < numOfItems*maxValueOfItem);
}


@Test
public void testBasic(){

    Knapsack k = new Knapsack(10);
    LinkedList<Item> itemLinkedList = new LinkedList<>();
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
        itemLinkedList.add(new Item(i, random.nextInt()%5 + 5, random.nextInt()%100 + 100));
    }

    KnapsackSolver knapsackSolver = new KnapsackSolver();

    knapsackSolver.solveKnapsackOptimal(k, itemLinkedList).toString();

}

@Test
public void testEasyGreedy(){

    Knapsack k = new Knapsack(10);
    KnapsackSolver knapsackSolver = new KnapsackSolver();
    System.out.println("testBegin");
    knapsackSolver.solveKnapsackGreedyStupid(k, (LinkedList<Item>) itemLinkedListGlo.clone());
    System.out.println("testEnd");
    System.out.println(k.currentValue);
    Assert.assertTrue(k.currentValue >= 35);
    Assert.assertTrue(k.getItemsInKnapsack().contains(itemLinkedListGlo.get(5)));
}


    @Test
    public void testLessEasyGreedy(){

        Knapsack k = new Knapsack(10);

        k = knapsackSolverGlo.solveKnapsackGreedySmart(k, (LinkedList<Item>) itemLinkedListGlo.clone());


        Assert.assertTrue(k.getItemsInKnapsack().contains(itemLinkedListGlo.get(5)));
    }

    @Test
    public void testJustOnePerfectItem(){

        Item itemSpe = new Item(10, 28, 81);
        itemLinkedListGlo.add(itemSpe);

        Knapsack k = knapsackSolverGlo.solveKnapsackOptimal(knapsackGlo, (LinkedList<Item>) itemLinkedListGlo.clone());

        Assert.assertTrue("There is more than one element in your knapsack",k.getItemsInKnapsack().size() == 1);
        Assert.assertTrue("The item is not the expected one" ,k.getItemsInKnapsack().get(0).equals(itemSpe));

    }

    @Test
    public void testItemsLikeSand(){

        for (int i = 0; i < knapsackGlo.getMaximumWeight(); i++) {
            itemLinkedListGlo.add(new Item(i, 1, 1));
        }

        Knapsack k = knapsackSolverGlo.solveKnapsackOptimal(knapsackGlo, (LinkedList<Item>) itemLinkedListGlo.clone());

        Assert.assertTrue("With sand, the little rest of the knapsnack is not filled!!!\n\n\n\n", k.getItemsInKnapsack().size() == 9);

    }


    @Test
    public void testJustSandAndOneDiffEl(){

        Knapsack k1 = new Knapsack(30);
        LinkedList<Item> sandItemsList = new LinkedList<>();

        for (int i = 0; i <= k1.getMaximumWeight(); i++) {
            if (i == k1.getCurrentWeight()){
                sandItemsList.add(new Item(i, 4, 4));
            }
            sandItemsList.add(new Item(i, 3, 3));
        }

        Knapsack k2 = knapsackSolverGlo.solveKnapsackOptimal(k1, sandItemsList);

        Assert.assertTrue("That does not have the optimum value", k2.currentValue == 30);
        Assert.assertTrue("There is a unnecessary element in the sack", k2.getItemsInKnapsack().size() == 10);


    }

    @Test
    public void testAllIsTooWorthy(){

        Knapsack k1 = new Knapsack(30);

        LinkedList<Item> itemLinkedList = new LinkedList<>();

        for (int i = 0; i < k1.getMaximumWeight(); i++) {
            itemLinkedList.add(new Item(i, k1.getMaximumWeight()+1, i*100));
        }

        itemLinkedList.add(new Item(31, 29, 1));

        k1 = knapsackSolverGlo.solveKnapsackOptimal(k1, itemLinkedList);

        Assert.assertTrue("That cant be so valueable!", k1.currentValue <= 1);
    }



}