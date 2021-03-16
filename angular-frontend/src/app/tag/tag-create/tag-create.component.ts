import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tag } from '../tag';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-tag-create',
  templateUrl: './tag-create.component.html',
  styleUrls: ['./tag-create.component.css']
})
export class TagCreateComponent implements OnInit {

  tag: Tag = new Tag();

  constructor(private tagService: TagService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveTag(){
    this.tagService.createTag(this.tag).subscribe( data => {
      this.goToTagList();
    },
    error => console.log(error));
  }

  goToTagList(){
    this.router.navigate(['/tags']);
  }

  onSubmit(){
    this.saveTag();
  }



}
