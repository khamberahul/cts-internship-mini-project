import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'centers'
})
export class CentersPipe implements PipeTransform {

  transform(value: any, args?: any): any {

    args = args?.toLowerCase();

    return value.filter((item: any) => {
      return JSON.stringify(item).toLowerCase().includes(args);
    })
  }

}
