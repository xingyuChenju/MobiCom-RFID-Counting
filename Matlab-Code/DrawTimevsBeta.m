close all
clear all
addpath('functions')
c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
alpha = 0.05; 

line1 = [];
line2 = [];
line3 = [];
line4 = [];

for i=0.02:0.02:0.2
    line4 = [line4;fun_get_UPE(alpha,i,10000)/1000]; % UPE time efficiency
    line3 = [line3;fun_get_BestQ(alpha,i,10000)/1000]; % ATD time efficiency
    line2 = [line2;fun_get_FNEB(alpha,i,10000)/1000]; % FNEB time efficiency
    line1 = [line1;fun_get_ZOE(alpha,i,10000)/1000]; % ZOE time efficiency
end

figure 
plot(0.02:0.02:0.2,line3,'Color',c{1},'LineWidth',0.75,'LineStyle','-','Marker','o','MarkerFaceColor',c{1})
hold on
plot(0.02:0.02:0.2,line2,'Color',c{3},'LineWidth',0.75,'LineStyle','-.','Marker','^','MarkerFaceColor',c{3})
hold on
plot(0.02:0.02:0.2,line1,'Color',c{4},'LineWidth',0.75,'LineStyle','-.','Marker','s','MarkerFaceColor',c{4})
hold on
plot(0.02:0.02:0.2,line4,'Color',c{2},'LineWidth',0.75,'LineStyle','-.','Marker','*','MarkerFaceColor',c{2})

%  ylim([0 5])
xlim([0.02 0.2])
fun_set_axis_size('\beta','Time (s)',16,[420 300]);
legend('ATD','FENB','ZOE','UPE','northeast')
grid on