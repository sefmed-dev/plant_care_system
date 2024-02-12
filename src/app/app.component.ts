import { Component, OnInit } from '@angular/core';
import { AngularFireModule } from '@angular/fire/compat';

import { AngularFireDatabase, AngularFireList, AngularFireObject} from '@angular/fire/compat/database';

import { Plant } from './plant';
import { map } from 'rxjs';
import Chart from 'chart.js/auto';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'plant_care_system';
  public chart1: any;
  public chart2: any;
  public chart3: any;
  public dates: any;
  public humidityData: any;
  public sol_humiU: number = 25;
  public humiU: number = 70;
  public tempU: number = 27;
  public temperatureData: any;
  public sol_humidityData: any;
  private historyRef: AngularFireList<Plant> ;
  public history?: Plant[];
  public realTime?: Plant;
  public linkArrosage?: String;
  public linkWindow?: String;
  public var1?: String;
  public var2?: String;
  private realTimePlant:AngularFireObject<Plant>;
  constructor(db: AngularFireDatabase){
    this.historyRef = db.list<Plant>('/plants/plants');
    this.realTimePlant = db.object<Plant>('/plants/realtimePlants');
  }

  ngOnInit(): void{
    this.historyRef
      .snapshotChanges()
      .pipe(map((changes) => changes.map((c)=>({ ...c.payload.val()}))))
      .subscribe((data) => {
        this.history = data;
        this.dates = this.history?.map(plant => plant.date);
        this.humidityData = this.history?.map(plant => plant.humidity);
        this.temperatureData = this.history?.map(plant => plant.temperature);
        this.sol_humidityData = this.history?.map(plant => plant.sol_humidity);

        this.chart1 = new Chart("MyChart1", {
          type: 'line',
    
          data: {
            labels: this.dates, 
             datasets: [
              {
                label: "température",
                data: this.temperatureData,
                backgroundColor: 'green'
              }
            ]
          },
          options: {
            aspectRatio:2.5
          }
        });
        this.chart2 = new Chart("MyChart2", {
          type: 'line',
    
          data: {
            labels: this.dates, 
             datasets: [
              {
                label: "Humidité",
                data: this.humidityData,
                backgroundColor: 'green'
              }
            ]
          },
          options: {
            aspectRatio:2.5
          }
          
        });
        this.chart3 = new Chart("MyChart3", {
          type: 'line',
    
          data: {
            labels: this.dates, 
             datasets: [
              {
                label: "Humidité du sol",
                data: this.sol_humidityData,
                backgroundColor: 'green'
              }
            ]
          },
          options: {
            aspectRatio:2.5
          }
          
        });
    });
    this.realTimePlant
      .snapshotChanges()
      .pipe(map((action) => ({ key: action.key, ...action.payload.val() })))
      .subscribe((data) => {
        this.realTime = data;
        this.onValueChange();
      });
  }
  onValueChange() {
    if (this.realTime?.temperature !== undefined && this.realTime?.humidity !== undefined && this.realTime?.temperature > this.tempU && this.realTime?.humidity > this.humiU) {
      this.linkWindow = "assets/window_on.gif";
    }else{
      this.linkWindow = "assets/no_action.jpg";
    }

    if (this.realTime?.sol_humidity !== undefined && this.realTime?.sol_humidity < this.sol_humiU) {
      this.linkArrosage = "assets/arrosage_on.gif";
    }else{
      this.linkArrosage = "assets/no_action.jpg";
    }

    if (this.linkArrosage=="assets/no_action.jpg"){
      this.var1 = "Non"
    }else{
      this.var1 = "Oui"
    }
    if (this.linkWindow=="assets/no_action.jpg"){
      this.var2 = "Non"
    }else{
      this.var2 = "Oui"
    }
  }
}
