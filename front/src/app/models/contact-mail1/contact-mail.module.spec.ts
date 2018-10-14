import { ContactMailModule } from './contact-mail.module';

describe('ContactMailModule', () => {
  let contactMailModule: ContactMailModule;

  beforeEach(() => {
    contactMailModule = new ContactMailModule();
  });

  it('should create an instance', () => {
    expect(contactMailModule).toBeTruthy();
  });
});
