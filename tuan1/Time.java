Time setMinute(int m) {
minute = (( m >= 0 && m < 60 ) ? m : 0 );
return this;
}
Time setSecond(int s) {
second = ((s >= 0 && s < 24 ) ? s : 0 );
return this;
}

int getHour() { return hour; }
int getMinute() { return minute; }
int getSecond() { return second; }