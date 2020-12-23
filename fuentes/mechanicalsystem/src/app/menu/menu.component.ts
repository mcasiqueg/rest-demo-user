import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {
    public menuItems: any[];

    isMobileMenu() {
        if ($(window).width() > 991) {
            return false;
        }
        return true;
    };

    ngOnInit() {
        //this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
}