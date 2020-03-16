def init(sequence):
    with open('reads-5000.fasta.sec') as input:
        sequence = "".join([line.strip() for line in input.readlines()[1:]])
    return sequence

def GCcontent(sequence):
    GCcount = 0
    for letter in sequence:
        if letter == "G" or letter == "C":
            GCcount += 1
    return GCcount

def main():
    sequence = ""
    sequence = init(sequence)
    print("GC Content is {:.1f}%".format(float(GCcontent(sequence)) / len(sequence)*100))

main()