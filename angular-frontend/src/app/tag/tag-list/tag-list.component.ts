import { Component, OnInit } from '@angular/core';
import { Tag } from '../tag';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-tag-list',
  templateUrl: './tag-list.component.html',
  styleUrls: ['./tag-list.component.css']
})
export class TagListComponent implements OnInit {

  tags: Tag[];

  constructor(private tagService: TagService) { }

  ngOnInit(): void {
    this.getTags();
  }

  private getTags(){
    this.tagService.getTagList().subscribe(data => {
      this.tags = data;
    });
  }

}
