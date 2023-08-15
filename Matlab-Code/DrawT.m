close all 
clear all
addpath('functions')

colors = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
f = 2^8;
f1 = 2^9;
f2 = 2^10;
ETS = zeros(2000,1);
ETS2 = zeros(2000,1);
ETS3= zeros(2000,1);
for n =  1:100000
    ETS(n) = fun_get_ET(f,n);
    ETS2(n) = fun_get_ET(f1,n);
    ETS3(n)= fun_get_ET(f2,n);
end
% x = 200:200:2000;
figure
semilogx(1:100000,ETS,'--','Color',colors{1},'LineWidth',1.2)
hold on
% plot(x,ETS(x),'o','Color','k')
% hold on
semilogx(1:100000,ETS2,'-.','Color',colors{2},'LineWidth',1.2)
hold on
% plot(x,ETS2(x),'^','Color','k')
% hold on
semilogx(1:100000,ETS3,'-','Color',colors{4},'LineWidth',1.2)
% hold on
% plot(x,ETS3(x),'s','Color','k')
ylim([0 800])
xlim([0 800])
xticks([10 100 1000])
fun_set_axis_size('Number of Tags','Average Time Duration (s)',16,[420 300]);
legend('f = 2^8','f = 2^9','f = 2^{10}','Location','northeast')
grid on
% semilogx(x,y,'o-')
% set(hl,'Orientation','horizon')