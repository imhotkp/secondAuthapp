import { Controller, Get, Render } from '@nestjs/common';

import { AppService } from './app.service';

// @Controller()
// export class AppController {
//   constructor(private readonly appService: AppService) {}

//   @Get()
//   getHello(): string {
//     return this.appService.getHello();
//   }
// }
// import { AppService } from './app.service';

@Controller('')
export class AppController {
  constructor(private readonly appService: AppService) {}
  @Get('login')
  @Render('login')
  login() {
    return ;
  }

  @Get('signup')
  @Render('signup')
  signup() {
    return;
  }
}