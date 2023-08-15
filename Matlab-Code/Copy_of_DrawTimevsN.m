close all
clear all
addpath('functions')
c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[204,102,2]/255,[174 32 18]/255};

line1=[1.80586907449210e+000
1.80586907449210e+000
1.81715575620767e+000
1.80586907449210e+000
1.80586907449210e+000
1.81715575620767e+000
1.81715575620767e+000
1.80586907449210e+000
1.81715575620767e+000
1.81715575620767e+000
1.79458239277652e+000]

line2=[1.45598194130926e+000
1.46726862302483e+000
1.46726862302483e+000
1.46726862302483e+000
1.46726862302483e+000
1.45598194130926e+000
1.45598194130926e+000
1.46726862302483e+000
1.46726862302483e+000
1.46726862302483e+000
1.47855530474041e+000]

line3=[2.25733634311512e-001
3.38600451467269e-001
4.17607223476298e-001
6.20767494356659e-001
8.91647855530474e-001
1.25282167042889e+000
1.81715575620767e+000
2.64108352144470e+000
3.87133182844244e+000
5.12415349887133e+000]

line4=[
4.40180586907449e-001
4.17607223476298e-001
4.28893905191874e-001
4.40180586907449e-001
4.28893905191874e-001
4.28893905191874e-001
4.40180586907449e-001
4.40180586907449e-001
4.17607223476298e-001
4.17607223476298e-001
4.17607223476298e-001]

line5=[
3.49887133182844e-001
3.04740406320542e-001
4.17607223476298e-001
3.49887133182844e-001
3.27313769751693e-001
4.28893905191874e-001
3.61173814898420e-001
3.49887133182844e-001
4.17607223476298e-001
3.27313769751693e-001
3.61173814898420e-001]


figure 
semilogx(10.^[3:0.2:5],line5,'Color',c{1},'LineWidth',0.75,'LineStyle','-','Marker','o','MarkerFaceColor',c{1})
hold on
semilogx(10.^[[3:0.2:4.6] 4.75],line3,'Color',c{2},'LineWidth',0.75,'LineStyle','-','Marker','*','MarkerFaceColor',c{2})
hold on
semilogx(10.^[3:0.2:5],line2,'Color',c{3},'LineWidth',0.75,'LineStyle','-.','Marker','p','MarkerFaceColor',c{3})
hold on
semilogx(10.^[3:0.2:5],line1,'Color',c{4},'LineWidth',0.75,'LineStyle','-.','Marker','^','MarkerFaceColor',c{4})
hold on
semilogx(10.^[3:0.2:5],line4,'Color',c{5},'LineWidth',0.75,'LineStyle','-.','Marker','s','MarkerFaceColor',c{5})
ylim([0 5])
fun_set_axis_size('Number of Tags','Time (s)',16,[420 300]);
legend('ATD','UPE','FENB','ZOE','SRC','Location','northwest')
grid on



% function [] = fun_draw_alpha(beta)
% n = 10000; 
% Times = [];
% % Times1 = [];
% Times2 = [];
% Times3 = [];
% for alpha = 0.02:0.02:0.1
%     Times =[Times; fun_get_BestQ(alpha,beta,n)];
% %     Times1 =[Times1; fun_get_ART(alpha,beta)];
%     Times2 =[Times2; fun_get_FNEB(alpha,beta,n)];
%     Times3 =[Times3; fun_get_UPE(alpha,beta,n)];
% end
% c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
% figure
% plot(  0.02:0.02:0.1,Times/1000,'--s','Color',c{1},'LineWidth',0.75)
% hold on
% % semilogy(  0.01:0.02:0.2,Times(1:2:20)/1000,'s','Color',c(1,:),'LineWidth',0.75)
% % hold on
% % semilogy(0.01:0.01:0.2,smooth(Times1)/1000,'-*','Color','k','LineWidth',0.75)
% % hold on
% plot(  0.02:0.02:0.1,Times2/1000,'-.o','Color',c{2},'LineWidth',0.75)
% hold on
% % semilogy(  0.01:0.02:0.2,Times2(1:2:20)/1000,'o','Color',c(3,:),'LineWidth',0.75)
% % hold on
% plot(  0.02:0.02:0.1,Times3/1000,'-*','Color',c{3},'LineWidth',0.75)
% % hold on
% % semilogy(  0.01:0.02:0.2,Times3(1:2:20)/1000,'*','Color',c(4,:),'LineWidth',0.75)
% 
% fun_set_axis_size('\alpha','Time (s)',16,[420 300])
% xticks(0.02:0.02:0.1)
% xlim([0.02 0.1])
% grid on
% legend('ATD','FNEB','UPE')
% end
