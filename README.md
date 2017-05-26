# Terrarium

## Terrarium Page
Click [here](https://jirayul.github.io/Terrarium/) to see my page.

## Introduction

Today, even a small convenience store has so many kind of items in their stock for them to manage. And things get even worse for a wholesale business, of which in their stock might be up to a thousand kinds of goods. The traditional paper-based solution are clumsy and error prone, so we think maybe it's the time for a computer-based solution for the of business. We expect this solution to help the wholesale business in managing their stock and keeping track of the price of each item.

This project was inspired by the **Wannasit** store. **Wannasit** store is my friend's wholesale and retail store located in the _Suphanburi_ Province. In the past, he used to ask me about creating a program for managing his family's store, so instead of teaching him I create one, will do it for him.

## Main Features

- Adding and removing products to store
- Calculate the prices, including the total price and taxes
- Safekeeping the sales data in the database
- Calculate the daily statistics of the sales

## Design Patterns
<table>
  <thead>
    <tr>
      <th style="text-align: left">Pattern</th>
      <th style="text-align: left">Description/Reason</th>
      <th style="text-align: left">Classes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align: left">Singleton</td>
      <td style="text-align: left">The singleton pattern was used in many classes within this project as a global data store.</td>
      <td style="text-align: left">TerrariumUI, PaymentUI, CashierMachine, ChangeUI, Store, ProductsSale, StatisticUI, Sales</td>
    </tr>
    <tr>
      <td style="text-align: left">Model-View-Controller (MVC)</td>
      <td style="text-align: left">The MVC pattern was used for separate application's concerns</td>
      <td style="text-align: left">Model : Store, Sales <br> View : all of UI <br> Controller : CashierMachine, ProductsSales</td>
    </tr>
    <tr>
      <td style="text-align: left">Composite</td>
      <td style="text-align: left">The Composite pattern was used in many UI.</td>
      <td style="text-align: left">All components of the JFrame and JFrame itself</td>
    </tr>
    <tr>
      <td style="text-align: left">Observer</td>
      <td style="text-align: left">The observer pattern was used in various GUI classes as it enables low coupling between the controller classes and the GUI itself.</td>
      <td style="text-align: left">Observer : PaymentUI, ChangeUI, TerrariumUI, StatisticUI <br> Observable : CashierMachine, ProductsSales</td>
    </tr>
  </tbody>
</table>

## User Interface

![Main Page](http://158.108.44.66:5000/uploads/TerrariumMainPage.png)

![PaymentUI](http://158.108.44.66:5000/uploads/TerrariumPayment.png)

![ChangeUI](http://158.108.44.66:5000/uploads/TerrariumChange.png)

![StatisticUI](http://158.108.44.66:5000/uploads/TerrariumStatistic.png)

## UML Diagram for Terrarium

![UML](http://158.108.44.66:5000/uploads/UML%20Class%20Diagram.png)

## Get It Running
### Download
Click [here](https://github.com/JirayuL/Terrarium/releases/latest) to get the latest version. Or go to [releases](https://github.com/JirayuL/Terrarium/releases) to see all versions

### Install
No installation required, use the command below to start the application
```
java -jar Terrarium.jar
```


## Value Proposition
### Why did this worth doing?
Because this software can help offloading the boring and error-prone job of stock keeping and accounting to computer-based system. And we expect that to help speed up the business operations and push the business forward.

### What did you learn?
- Using softwares to help businesses in their operations
- Using `Actions` with `JTable`
- Utilizing database as a storage for Java application

## Contributors

- [Jirayu Laungwilawan](https://github.com/JirayuL)
- [Wanchanapon Thanwaranurak](https://github.com/PaiizZ)
