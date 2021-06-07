import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/service';
import { Tag } from 'app/shared/models/tag';

@Component({
  selector: 'app-admin-tags',
  templateUrl: './admin-tags.component.html',
  styleUrls: ['./admin-tags.component.css']
})
export class AdminTagsComponent implements OnInit {

  tags: Tag[];
  tag: Tag = new Tag;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getTags();
  }

  private getTags(){
    this.userService.getTagList().subscribe(data => {
      this.tags = data;
    });
  }

  private addTag() {
    this.userService.addTag(this.tag).then(data => {
      console.log(data);
      this.tags = data;
      this.tag.name = "";
    });
  }

  private tagDelete(tag: Tag){
    this.userService.deleteTag(tag).then(data => {
      this.tags = data;
    });
  }

}
