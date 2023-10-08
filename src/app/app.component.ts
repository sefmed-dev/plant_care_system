import { Component, OnInit } from '@angular/core';
import { AngularFireModule } from '@angular/fire/compat';

import { AngularFireDatabase, AngularFireList } from '@angular/fire/compat/database';


import { Plant } from './plant';
import { map } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'plant_care_system';
  private historyRef: AngularFireList<Plant> ;
  public history?: Plant[];
  constructor(db: AngularFireDatabase){
    this.historyRef = db.list<Plant>('/plants');
  }

  ngOnInit(): void{
    this.historyRef
      .snapshotChanges()
      .pipe(map((changes) => changes.map((c)=>({ ...c.payload.val()}))))
      .subscribe((data) => {
        this.history = data;
    });
  }
}
