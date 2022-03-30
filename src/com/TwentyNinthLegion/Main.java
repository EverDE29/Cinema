package com.TwentyNinthLegion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        final char[][] cinema = new char[rows + 1][seatsPerRow + 1];

        char zero = '0';

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
                cinema[0][0] = ' ';
                cinema[i][0] = zero;
                cinema[0][j] = zero;
            }
            zero++;
        }

        zero = '0';

        for (int a = 0; a < seatsPerRow + 1; a++) {
            for (int b = 0; b < rows + 1; b++) {
                cinema[0][a] = zero;
                cinema[0][0] = ' ';
            }
            zero++;
        }

        StringBuilder builder = new StringBuilder();
        for (char[] value : cinema) {
            builder.append(value).append("\n");
        }
        String text = builder.toString();

        StringBuilder cinemaLayout = new StringBuilder(text.replace("", " "));

        int numOfSeats = rows * seatsPerRow;
        int backRows = rows - (rows / 2);
        int frontRows = rows - backRows;
        int ticketPrice;
        int firstHalfTickets;
        int secondHalfTickets;
        boolean yeahThatsTrue = true;
        boolean loop = false;
        int choice;
        int numberOfTickets = 0;
        int currentIncome = 0;
        int total = 0;
        float occupiedSeats = 0;
        float percentage = 0;

        if (numOfSeats < 60) {
            ticketPrice = 10;
            total = rows * seatsPerRow * ticketPrice;
        } else if (numOfSeats > 60) {
            firstHalfTickets = 10;
            secondHalfTickets = 8;
            total = (backRows * seatsPerRow * secondHalfTickets) + (frontRows * seatsPerRow * firstHalfTickets);
        }

        while (yeahThatsTrue) {
            System.out.printf("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.printf("Cinema:\n%s\n", cinemaLayout);
                    break;
                case 2:
                    int rowNum = 0;
                    int seatNum = 0;

                    do {
                        try {
                            System.out.println("Enter a row number:");
                            rowNum = scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            seatNum = scanner.nextInt();
                            loop = true;
                            if (cinema[rowNum][seatNum] == 'B') {
                                System.out.println("That ticket has already been purchased!");
                                loop = false;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Wrong Input!");
                            scanner.nextLine();
                            loop = false;
                        }
                    }
                    while (!loop);

                    cinema[rowNum][seatNum] = 'B';

                    if (numOfSeats < 60) {
                        ticketPrice = 10;
                        System.out.printf("Ticket price: $%d\n", ticketPrice);
                        total = rows * seatsPerRow * ticketPrice;
                        currentIncome += ticketPrice;
                    } else if (numOfSeats > 60) {
                        firstHalfTickets = 10;
                        secondHalfTickets = 8;
                        total = (backRows * seatsPerRow * secondHalfTickets) + (frontRows * seatsPerRow * firstHalfTickets);                        if (rowNum > frontRows) {
                            System.out.printf("Ticket price: $%d\n", secondHalfTickets);
                            currentIncome += secondHalfTickets;
                        } else {
                            System.out.printf("Ticket price: $%d\n", firstHalfTickets);
                            currentIncome += firstHalfTickets;
                        }
                    }

                    numberOfTickets++;
                    occupiedSeats++;

                    percentage = (occupiedSeats / numOfSeats) * 100;

                    StringBuilder builder2 = new StringBuilder();
                    for (char[] value : cinema) {
                        builder2.append(value).append("\n");
                    }
                    String text2 = builder2.toString();

                    StringBuilder updatedLayout = new StringBuilder(text2.replace("", " "));

                    cinemaLayout = updatedLayout;
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d%n", numberOfTickets);
                    System.out.printf("Percentage: %.2f", percentage).print("%");
                    System.out.printf("%nCurrent income: $%d%nTotal income: $%d%n", currentIncome, total);
                    break;
                case 0:
                    yeahThatsTrue = false;
                    break;
                default:
                    break;

            }
        }

    }

}
