<!doctype html>
<html lang="en" ng-app="gmines" class="no-js">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <title>GMines</title>
  <asset:stylesheet href="application.css"/>
</head>
<body ng-controller="MasterCtrl">
  <div id="page-wrapper" ng-class="{'open': toggle}" ng-cloak>

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
      <ul class="sidebar">
        <li class="sidebar-main">
          <a ng-click="toggleSidebar()">
            GMines
            <span class="menu-icon glyphicon glyphicon-transfer"></span>
          </a>
        </li>
        <li class="sidebar-title"><span>NAVIGATION</span></li>
        <li class="sidebar-list">
          <a href="#/">Home <span class="menu-icon fa fa-home"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/report">Lobby <span class="menu-icon fa fa-list"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/report">Stats <span class="menu-icon fa fa-tachometer"></span></a>
        </li>
        <li class="sidebar-title"><span>Playing now</span></li>
        <li class="sidebar-list">
          <a href="#/game/1">Fred (5 - 8)<span class="menu-icon fa fa-table"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/game/1">Fred (5 - 8)<span class="menu-icon fa fa-table"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/game/1">Fred (5 - 8)<span class="menu-icon fa fa-table"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/game/1">Fred (5 - 8)<span class="menu-icon fa fa-table"></span></a>
        </li>
        <li class="sidebar-title"><span>GAMES OVER</span></li>
        <li class="sidebar-list">
          <a href="#/game/2">Jessica (26 - 8)<span class="menu-icon fa fa-table"></span></a>
        </li>
      </ul>
      <div class="sidebar-footer">
        <div class="col-xs-4">
          <a href="https://github.com/stephanebruckert/GMines/" target="_blank">
            Github
          </a>
        </div>
        <div class="col-xs-4">
          <a href="https://github.com/stephanebruckert/GMines/README.md" target="_blank">
            About
          </a>
        </div>
        <div class="col-xs-4">
          <a href="https://github.com/stephanebruckert/GMines/issues">
            Support
          </a>
        </div>
      </div>
    </div>
    <!-- End Sidebar -->

    <div id="content-wrapper">
      <div class="page-content">

        <!-- Header Bar -->
        <div class="row header">
          <div class="col-xs-12">
            <div class="user pull-right">
              <div class="item dropdown">
                <a href="#" class="dropdown-toggle">
                  <img src="../assets/avatar.jpg">
                </a>
                <ul class="dropdown-menu dropdown-menu-right">
                  <li class="dropdown-header">
                    Joe Bloggs
                  </li>
                  <li class="divider"></li>
                  <li class="link">
                    <a href="#">
                      Profile
                    </a>
                  </li>
                  <li class="link">
                    <a href="#">
                      Menu Item
                    </a>
                  </li>
                  <li class="link">
                    <a href="#">
                      Menu Item
                    </a>
                  </li>
                  <li class="divider"></li>
                  <li class="link">
                    <a href="#">
                      Logout
                    </a>
                  </li>
                </ul>
              </div>
              <div class="item dropdown">
               <a href="#" class="dropdown-toggle">
                  <i class="fa fa-bell-o"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-right">
                  <li class="dropdown-header">
                    Notifications
                  </li>
                  <li class="divider"></li>
                  <li>
                    <a href="#">Server Down!</a>
                  </li>
                </ul>
              </div>
            </div>
            <div class="meta pull-right" ng-show="selected.data">
              <div class="page">
                {{ selected.data }}
              </div>
              <div class="breadcrumb-links">
                  <a role="button" ng-click="doLogout()">Disconnect</a>
              </div>
            </div>
            <div class="meta">
              <div class="page">
                Dashboard
              </div>
              <div class="breadcrumb-links">
                Home / Dashboard
              </div>
            </div>
          </div>
        </div>
        <!-- End Header Bar -->
        <!-- Main Content -->
        <div ng-controller="LoginController">
          <div ui-view>
            Loading...
          </div>
        </div>
        <asset:javascript src="application.js"/>
      </div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
  </div><!-- End Page Wrapper -->
</body>
</html>