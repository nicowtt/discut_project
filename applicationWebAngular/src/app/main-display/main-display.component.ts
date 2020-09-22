import { ComplainResponseModel } from './../models/ComplainResponse.model';
import { BottleModel } from './../models/Bottles.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AlertService } from './../services/alert.service';
import { ComplainUserModel } from './../models/ComplainUser.model';
import { AuthService } from './../services/auth.service';
import { Router } from '@angular/router';
import { ComplainRequestService } from './../services/ComplainRequest.service';
import { ComplainRequestModel } from './../models/ComplainRequest.model';
import { ComplainThemeService } from './../services/ComplainTheme.service';
import { Subscription } from 'rxjs';
import { ComplainThemeModel } from './../models/ComplainTheme.model';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-main-display',
  templateUrl: './main-display.component.html',
  styleUrls: ['./main-display.component.css']
})
export class MainDisplayComponent implements OnInit, OnDestroy {

  themesList: ComplainThemeModel[];
  themesSubscription: Subscription;

  requestsList: ComplainRequestModel[];
  requestsSubscription: Subscription;

  currentUser: ComplainUserModel;
  userConnected: boolean;

  bottles: BottleModel[] = new Array();

  constructor(private complainThemeService: ComplainThemeService,
              private complainRequestService: ComplainRequestService,
              private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private snackBar: MatSnackBar,
  ) {
    this.authService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    // subscription
    this.themesSubscription = this.complainThemeService.themeSubject.subscribe(
      (themes: ComplainThemeModel[]) => {
        this.themesList = themes;
      }
    );
    this.complainThemeService.getAllThemes(() => {
    });

    this.requestsSubscription = this.complainRequestService.requestsSubject.subscribe(
      (requests: ComplainRequestModel[]) => {
        this.requestsList = requests;
      }
    );
    this.complainRequestService.getAllRequests(() => {
      this.complainRequestService.emitRequests();
      this.requestsList.forEach(request => {
        this.countNbrOfResponse(request);
        request.creationDayUntilToday = this.calculateDiffFromTodayTo(request.creationDate);
        this.fillBottles();
      });
      this.requestsList.sort(this.comparePopularity); // bigger is upper

    });
    // user connected
    if (this.authService.currentUserValue) {
      this.userConnected = true;
    } else {
      this.userConnected = false;
    }
  }

  ngOnDestroy() {
    this.themesSubscription.unsubscribe();
    this.requestsSubscription.unsubscribe();
  }

  comparePopularity(a, b) {
    const requestA = a.popularity;
    const requestB = b.popularity;

    let comparison = 0;
    if (requestA < requestB) {
      comparison = 1;
    } else if (requestA > requestB) {
      comparison = -1;
    }
    return comparison;
  }

  showResponses(index: number) {
    this.router.navigate(['/response', this.requestsList[index].id]);
  }

  countNbrOfResponse(request: ComplainRequestModel) {
    const nbrOfResponse = request.complainResponsesId.length;
    request.nbrResponse = nbrOfResponse;
  }

  changePopularity(index: number, change: string) {
    if (this.userConnected) {
      if (change === '+') {
        this.requestsList[index].popularity++;
      } else if (change === '-') {
        this.requestsList[index].popularity--;
      }

      const userPseudo = this.currentUser.pseudo;
      this.complainRequestService.changeRequestPopularity(this.requestsList[index], userPseudo, () => {
        // on error
        if (change === '+') {
          this.requestsList[index].popularity--;
        } else if (change === '-') {
          this.requestsList[index].popularity++;
        }
      });
    } else {
      this.snackBar.open('Vous devez être connecté pour avoir accés à cette fonction', '', {
        duration: 3000,
        verticalPosition: 'top'
      }
      );
    }
    this.requestsList.sort(this.comparePopularity);
  }

  calculateDiffFromTodayTo(dateSent) {
    const currentDate = new Date();
    dateSent = new Date(dateSent);

    return Math.floor((Date.UTC(currentDate.getFullYear(), currentDate.getMonth(),
    currentDate.getDate()) - Date.UTC(dateSent.getFullYear(), dateSent.getMonth(),
    dateSent.getDate())) / (1000 * 60 * 60 * 24));
  }

  fillBottles() {
    // INFO :poxXbottle -> min 0px, max 750px(picture) -> last response on request :today = 0px, tomorrow = 100px
    let posXbottle = 0;
    // create bottles
    this.requestsList.forEach(request => {
      posXbottle = this.calculateDiffFromTodayTo(request.lastResponseDate);
      if (posXbottle !== 0) {
        posXbottle = posXbottle * 100;
      }
      const bottle = new BottleModel('300px', posXbottle + 'px');
      bottle.requestName = request.request;
      // check on console
      console.log(bottle);
      // for now we see bottle 7 days after request is posted
      if (posXbottle <= 700) {
        this.bottles.push(bottle);
      }
    });
  }
}
