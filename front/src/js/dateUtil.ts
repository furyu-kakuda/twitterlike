import moment from 'moment';

export const format = (dayWithNotFormated: number): string => {
  return moment(dayWithNotFormated).format('MM/DD HH:mm');
};
