

def limpa(a,b,c,d):
 
  path = "r2/out_"+a+"_"+c+"_"+d+".txt"
  path2 = "dados1/out_"+a+"_"+b+"_"+c+".txt"
  arq = open(path) 
  t = ""
  r = "0.0"
  g = "0.0"
  n ="1"
  s = ''
  
  arq2 = open(path2,'w') if d=='0' else open(path2,'a')
  if d=='0':
    arq2.write("0\t0.0"+"\t"+g+"\t"+n+"\t"+s+"\n")
  cal = 48*3600
  t=[]
  r=[]
  for l in arq.readlines():
    dados = l.rstrip("\n").split(" ")
    dados1 = l.rstrip("\n").split("\t")
    if dados[0]=="Leitura":
      t.append("erro")
      r.append(0)
      print(dados)
    if dados1[0]=="VS":
      t[len(t)-1] = dados1[1]
      if dados1[2] == b:
        r[len(t)-1]+=float( dados1[4])
      if dados1[3] == b:
   
        r[len(t)-1]+= 2 - float( dados1[4])
  print(t)
  print(r)
  for i in range(len(t)):
    arq2.write(t[i]+"\t"+str(r[i])+"\n")
 

  arq2.close()
  arq.close()
  return
 

#aa = ["0"]
aa = [ str(x) for x in range(0,3)] 

bb = [str(x) for x in range(0,4)]
cc = [ str(x) for x in range(0,10)] 
dd = [ str(x) for x in range(0,2)] 

for a in aa:
  for b in bb:
    for c in cc:
      for d in dd:
        print(a,b,c,d)
        limpa(a,b,c,d)
