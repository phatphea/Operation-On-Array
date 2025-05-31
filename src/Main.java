import java.util.InputMismatchException;
import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static String Red = "\u001B[31m";
    public static String Green = "\u001B[32m";
    public static String Reset = "\u001B[0m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("       Welcome to Operations on Array      ");
        System.out.println("===========================================");

        System.out.print("Enter length of array: ");
        int arrLength = scanner.nextInt();

        //declare array 1D
        int[] arr = new int[arrLength];

        //assign value to arr
        int i=0;
        while (arrLength>0){
            System.out.print("Enter a number for arr["+i+"]: ");
            arr[i] = scanner.nextInt();
            i++;
            --arrLength;
        }

        //choose option to execute operation
        int option;
        while (true){

            //show option
            printOption();

            System.out.print("Choose an option to perform operation -> ");
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline

                if(option == 0){
                    System.out.println(Green+"Exited the program. Good Bye!"+Reset);
                    break;
                }
                if(option<0){
                    System.out.println(Red+"Option number can not less than 0."+Reset);
                    continue;
                }else {
                    //validate case by switch
                    switch (option){
                        case 1:
                            printArray(arr);
                            break;
                        case 2:
                            if(sortArray(arr)==true){
                                System.out.println(Green+"Sort Array Success!"+Reset);
                                break;
                            }
                        case 3:
                            if(reverseSortedArray(arr)==true){
                                System.out.println(Green+"Reverse Sorted Array Success!"+Reset);
                                break;
                            }
                        case 4:
                            if(reverseUnSortedArray(arr)==true){
                                System.out.println(Green+"Reverse Unsorted Array Success!"+Reset);
                                break;
                            }
                        case 5:
                            printArrayByIndex(arr);
                            int[] arrAfterInsert = insertElement(arr);
                            if(arrAfterInsert.length >0){
                                arr = arrAfterInsert;
                                System.out.println(Green+"Insert Element Success!"+Reset);
                            }
                            break;
                        case 6:
                            printArrayByIndex(arr);
                            if(updateElement(arr)==true){
                                System.out.println(Green+"Update Element Success!"+Reset);
                            }
                            break;
                        case 7:
                            printArrayByIndex(arr);
                            int[] arrAfterDelete = deleteElement(arr);
                            if(arrAfterDelete.length >0){
                                arr = arrAfterDelete;
                                System.out.println(Green+"Delete Element Success!"+Reset);
                            }
                            break;
                        default:
                            System.out.println(Red+"Invalid option. Please try again."+Reset);
                    }
                }

            }catch (InputMismatchException e){
                System.out.println(Red+"Invalid input: Please enter a valid number."+Reset);
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    public static void printOption(){
        System.out.print("""
                =============== Menu ===============
                1. Show Array                     ||
                2. Sort Array                     ||   
                3. Reverse sorted Array           ||
                4. Reverse unsorted Array         ||
                5. Insert an element              ||
                6. Update an element              ||
                7. Delete an element              ||
                0. Exit program                   ||
                ====================================
                """);
    }

    public static void printArray(int[] arr){
        System.out.print("Elements of Array: ");
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.print("\n");
    }

    public static void printArrayByIndex(int[] arr){
        System.out.println("Last element in array: ");
        for(int i=0; i<arr.length; ++i){
            System.out.println("Index["+i+"]: " + arr[i]);
        }
    }

    public static boolean sortArray(int[] arr){
        //bubble sort
        for(int i=0; i<arr.length-1; ++i){
            for(int j=0;j<arr.length - 1 - i; ++j){
                if(arr[j] > arr[j+1]){
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        // Validate if sorted
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean reverseSortedArray(int[] arr){
        // Clone original array for validation
        int[] original = arr.clone();

        int start = 0;
        int end = arr.length-1;

        while (start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
            --end;
        }
        // Validate: check if arr is reverse of original
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != original[original.length - 1 - i]) {
                return false; // Mismatch found
            }
        }

        return true;
    }

    public static boolean reverseUnSortedArray(int[] arr){
        // Simple selection sort in descending order
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            // Swap arr[i] and arr[maxIndex]
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
        if(arr[0]<arr[arr.length-1]){
            return false;
        }else {
            return true;
        }
    }

    public static int[] insertElement(int[] arr){
        Scanner scanner = new Scanner(System.in);

        boolean isStop = true;
        while (isStop==true){
            try {
                System.out.print("Enter position you want to insert: ");
                int position = scanner.nextInt();
                System.out.print("Enter value to insert: ");
                int value = scanner.nextInt();

                //create new array for inserting element
                int[] newArr = new int[arr.length+1];

                for(int i=0, j=0; i<newArr.length; i++){
                    if(i==position){
                        newArr[i] = value;
                    }else {
                        newArr[i] = arr[j];
                        j++;
                    }
                }

                arr = newArr; //assign new value to arr

                isStop=false;

            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println(Red+"Error: The position you entered is out of bounds! Please enter a position between 0 and " + arr.length + "."+Reset);
                isStop = true;
            }
        }
        return arr;
    }

    public static boolean updateElement(int[] arr){
        Scanner scanner = new Scanner(System.in);

        boolean isStop = true;
        while (isStop==true){
            try {
                System.out.print("Enter position you want to update: ");
                int position = scanner.nextInt();
                System.out.print("Enter new value to update: ");
                int newValue = scanner.nextInt();

                arr[position] = newValue;
                isStop=false;

            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println(Red+"Error: The position you entered is out of bounds! Please enter a position between 0 and " + (arr.length - 1) + "."+Reset);
                isStop = true;
            }
        }

        if(isStop == false) {
            return true;
        }else {
            return false;
        }
    }

    public static int[] deleteElement(int[] arr){
        Scanner scanner = new Scanner(System.in);

        boolean isStop = true;
        while (isStop==true){
            try {
                System.out.print("Enter element you want to delete: ");
                int elementToDelete = scanner.nextInt();

                // Validate if element to delete is not found in array
                boolean found = false;
                int count =0;
                for (int num : arr) {
                    if (num == elementToDelete) {
                        count++;
                        found = true;
                    }
                }
                if (!found) {
                    throw new Exception(Red+"Element not found in array."+Reset);
                }

                //create new array with adjust size after count element to delete
                int[] newArr = new int[arr.length-count];

                for(int i=0, j=0; i<arr.length; i++){
                    if(arr[i] != elementToDelete){
                        newArr[j]=arr[i];
                        j++;
                    }
                }

                arr = newArr; //assign new value to arr
                isStop=false;

            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                isStop = true;
            }
        }
        return arr;
    }
}