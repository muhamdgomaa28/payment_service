import { MatButtonModule, MatCheckboxModule, MatToolbar, MatToolbarModule, MatGridListModule, MatGridTileText, MatInputModule, MatFormField, MatOptionModule, MatSelectModule, MatMenuModule, MatIconModule, MatCardModule, MatSlideToggleModule, MatListModule, MatDialogModule, MatTabsModule } from '@angular/material';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [MatButtonModule, MatCheckboxModule,MatToolbarModule,MatGridListModule,MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatSlideToggleModule,
MatListModule,
MatDialogModule,
MatTabsModule,



],
  exports: [MatButtonModule, MatCheckboxModule,MatToolbarModule,MatGridListModule,MatInputModule,
    MatOptionModule,
    MatSelectModule, MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,MatSlideToggleModule,
    MatListModule
,MatDialogModule,
MatTabsModule

],
})
export class MyOwnCustomMaterialModule { }