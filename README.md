# ShoppingCartDemo

A demo app that adopts a MVVM architecture

Assumption:
Since there is no real network request, data retrieval is simulated via local asset

Must also check out this repo https://github.com/pjwingold/CommonLib, in order to run 

Libraries:

Google architectural component

ViewModel: network calls can survive orientation change, no activity leak

LiveData: notify UI(view) about changes from downstream

Navigation: greatly simplified navigation between screens with Bottom Nav support

BottomNavigationView: Easy switch between products and cart content

RecylerView: to display data in a list

Constraintlayout: flexible UI component arrangement

Kotlin coroutine: multi requests handling

mockitok: powerful mocking library for kotlin unit test

todo: 
cart content persistence to be added
